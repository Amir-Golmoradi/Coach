using Coach.Configuration;
using Coach.Models.Authentication;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using StackExchange.Redis;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Text.Json;
using Coach.Model.User;

namespace Coach.Service.Users;

public class RedisUsersService
{
    private readonly IDatabase _redis;
    private readonly JwtBearerOptionsConfig _jwtBearerOptions;
    private readonly TokenValidationParameters _tokenValidationParameters;

    private const string UserIdByEmailHashKey = "users:idsByEmail";
    private const string UserIdByUsernameHashKey = "users:idsByUsername";
    private const string UserByIdHashKey = "users:byId";
    private const string UserCountStringKey = "users:Ids";

    public RedisUsersService(IDatabase redis, JwtBearerOptionsConfig jwtBearerOptions)
    {
        _redis = redis;

        _jwtBearerOptions = jwtBearerOptions;

        _tokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = true,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,
            ValidIssuer = _jwtBearerOptions.Issuer,
            ValidAudience = _jwtBearerOptions.Audience,
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_jwtBearerOptions.Secret!))
        };
    }

    public async Task<User?> RegisterUserAsync(UserRegistration registration)
    {
        // Check if user already exists
        if (await GetUserByUsernameAsync(registration.Username) is not null ||
            await GetUserByEmailAsync(registration.Email) is not null) return null;

        // Set user ID from Redis increment
        var id = (await _redis.StringIncrementAsync(UserCountStringKey)).ToString();

        // Generate salt and hash password
        var salt = BCrypt.Net.BCrypt.GenerateSalt();
        var hashedPassword = BCrypt.Net.BCrypt.HashPassword(registration.Password, salt);

        User user = new(id, registration.Username, registration.Email, hashedPassword);

        // Add user hash to Redis
        await _redis.HashSetAsync(UserIdByEmailHashKey, user.Email, user.Id);
        await _redis.HashSetAsync(UserIdByUsernameHashKey, user.Username, user.Id);
        await _redis.HashSetAsync(UserByIdHashKey, user.Id, JsonSerializer.Serialize(user));

        return user;
    }

    public async Task<User?> LoginUserAsync(UserLogin login)
    {
        // Get a user by username or email
        var user = await GetUserByUsernameAsync(login.UsernameOrEmail) ??
                   await GetUserByEmailAsync(login.UsernameOrEmail);

        // Assert user exists
        if (user == null) return null;

        // Verify the password is correct
        if (!BCrypt.Net.BCrypt.Verify(login.Password, user.PasswordHash)) return null;

        return user;
    }

    public string GenerateUserJWTToken(User user)
    {
        if (user is null) throw new ArgumentNullException(nameof(user));

        // Create claims for the JWT token
        var claims = new[]
        {
            new Claim(ClaimTypes.NameIdentifier, user.Id),
            new Claim(ClaimTypes.Email, user.Email),
            new Claim(ClaimTypes.Name, user.Username)
        };

        // Generate the JWT token
        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_jwtBearerOptions.Secret!));
        var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
        var token = new JwtSecurityToken(
            _jwtBearerOptions.Issuer,
            _jwtBearerOptions.Audience,
            claims,
            expires: DateTime.Now.AddDays(7),
            signingCredentials: creds);

        return new JwtSecurityTokenHandler().WriteToken(token);
    }

    private string? GetUserIdFromJWTToken(string token)
    {
        try
        {
            var handler = new JwtSecurityTokenHandler();

            var claimsPrincipal = handler.ValidateToken(token, _tokenValidationParameters, out var validatedToken);

            var jwtToken = (JwtSecurityToken)validatedToken;

            var userId = jwtToken.Claims.First(c => c.Type == ClaimTypes.NameIdentifier).Value;

            return userId;
        }
        catch // On ivalid token
        {
            return null;
        }
    }

    public async Task<User?> GetUserByJWTToken(string token)
    {
        var userId = GetUserIdFromJWTToken(token);

        return userId is not null ? await GetUserByIdAsync(userId) : null;
    }

    public string? GetUserIdFromAuth(ControllerBase controller)
    {
        var token = controller.HttpContext.Request.Headers["Authorization"].ToString();

        return GetUserIdFromJWTToken(token.Replace("Bearer ", ""));
    }

    public async Task<User?> GetUserFromAuth(ControllerBase controller)
    {
        var userId = GetUserIdFromAuth(controller);

        if (userId is null) return null;

        return await GetUserByIdAsync(userId);
    }

    public async Task<User?> GetUserByIdAsync(string userId)
    {
        var json = await _redis.HashGetAsync(UserByIdHashKey, userId);

        if (!json.HasValue) return null;

        return JsonSerializer.Deserialize<User>(json!);
    }

    public async Task<User?> GetUserByEmailAsync(string email)
    {
        string? userId = await _redis.HashGetAsync(UserIdByEmailHashKey, email);

        if (string.IsNullOrEmpty(userId)) return null;

        return await GetUserByIdAsync(userId);
    }

    public async Task<User?> GetUserByUsernameAsync(string username)
    {
        string? userId = await _redis.HashGetAsync(UserIdByUsernameHashKey, username);

        if (string.IsNullOrEmpty(userId)) return null;

        return await GetUserByIdAsync(userId);
    }

    public async Task<bool> RemoveUserAsync(string userId)
    {
        var user = await GetUserByIdAsync(userId);

        if (user is null) return false;

        var removeUser = await _redis.HashDeleteAsync(UserByIdHashKey, user.Id);
        var removeEmail = await _redis.HashDeleteAsync(UserIdByEmailHashKey, user.Email);
        var removeUsername = await _redis.HashDeleteAsync(UserIdByUsernameHashKey, user.Username);

        return removeUser && removeEmail && removeUsername;
    }

    public async Task<string> GetUserCount()
    {
        string? count = await _redis.StringGetAsync(UserCountStringKey);

        return count ?? "0";
    }
}