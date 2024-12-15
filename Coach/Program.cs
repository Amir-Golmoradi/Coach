using Coach.Configuration;
using Coach.Controller.Leaderboards;
using Coach.Services.Leaderboard;
using Coach.Services.Workouts;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using StackExchange.Redis;
using System.Text;
using Coach.Service.Users;

internal class Program
{
    private static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        // Add services to the container.
        builder.Services.AddControllers();

        ConfigureSwagger(builder);

        ConfigureRedis(builder);

        ConfigureJwtAuth(builder);

        ConfigureCustomServices(builder);

        builder.Services.AddSignalR();

        var app = builder.Build();

        // Configure the HTTP request pipeline.
        if (app.Environment.IsDevelopment())
        {
            app.UseSwagger();
            app.UseSwaggerUI();
        }

        // app.UseHttpsRedirection();

        app.UseAuthorization();

        app.MapControllers();

        MapHubs(app);

        InstantiateServices(app);

        app.Run();
    }

    private static void ConfigureSwagger(WebApplicationBuilder builder)
    {
        builder.Services.AddEndpointsApiExplorer();
        builder.Services.AddSwaggerGen();
    }

    private static void ConfigureRedis(WebApplicationBuilder builder)
    {
        var config = builder.Configuration.GetSection("RedisConnection").Get<RedisConnectionConfig>()
                     ?? throw new Exception("'RedisConnection' section not found in 'appsettings.json'");


        builder.Services.AddSingleton<IConnectionMultiplexer>(provider =>
            ConnectionMultiplexer.Connect(config.ConnectionString!)); // Add IConnectionMultiplexer service to DI
        builder.Services.AddSingleton(provider =>
            provider.GetRequiredService<IConnectionMultiplexer>().GetDatabase()); // Add IDatabase service to DI
    }

    private static void ConfigureJwtAuth(WebApplicationBuilder builder)
    {
        var config = builder.Configuration.GetSection("JwtBearerOptions");

        builder.Services.Configure<JwtBearerOptionsConfig>(config);

        builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
            .AddJwtBearer(options =>
            {
                var jwtBearerOptions = config.Get<JwtBearerOptionsConfig>()
                                       ?? throw new Exception(
                                           "'JwtBearerOptions' section not found in 'appsettings.json'");

                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidateLifetime = true,
                    ValidateIssuerSigningKey = true,
                    ValidIssuer = jwtBearerOptions.Issuer!,
                    ValidAudience = jwtBearerOptions.Audience!,
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(jwtBearerOptions.Secret!))
                };
            });
    }

    private static void ConfigureCustomServices(WebApplicationBuilder builder)
    {
        // RedisUserService
        builder.Services.AddSingleton(provider =>
        {
            var redis = provider.GetRequiredService<IDatabase>();
            var jwtBearerOptions = provider.GetRequiredService<IOptions<JwtBearerOptionsConfig>>().Value;

            return new RedisUsersService(redis, jwtBearerOptions);
        });

        builder.Services.AddSingleton<WorkoutService>();

        builder.Services.AddSingleton<CalorieLeaderboardService>();
    }

    private static void MapHubs(WebApplication app)
    {
        app.MapHub<CalorieLeaderboardHub>("/Leaderboard/Calories/Hub");
    }

    // Used for services that need to be instantiated before app runs (i.e. Event-subscribers)
    private static void InstantiateServices(WebApplication app)
    {
        app.Services.GetService<CalorieLeaderboardService>();
    }
}