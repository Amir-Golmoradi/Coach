using Coach.Models.Authentication;
using Coach.Service.Users;
using Microsoft.AspNetCore.Mvc;

namespace Coach.Controller.User;

[ApiController]
[Route("[controller]/[action]")]
public class AuthenticationController : ControllerBase
{
    private readonly RedisUsersService _usersService;

    public AuthenticationController(RedisUsersService usersService)
    {
        _usersService = usersService;
    }

    [HttpPost]
    public async Task<IActionResult> Registration([FromBody] UserRegistration registration)
    {
        var user = await _usersService.RegisterUserAsync(registration);
        if (user == null) return BadRequest("User with this email/username already exists");

        var jwtBearer = _usersService.GenerateUserJWTToken(user);

        return Ok(new Dictionary<string, string> { { "jwtBearer", jwtBearer } });
    }

    [HttpPost]
    public async Task<IActionResult> Login([FromBody] UserLogin login)
    {
        var user = await _usersService.LoginUserAsync(login);
        if (user == null) return BadRequest("Invalid credentials");

        var jwtBearer = _usersService.GenerateUserJWTToken(user);

        return Ok(new Dictionary<string, string> { { "jwtBearer", jwtBearer } });
    }
}