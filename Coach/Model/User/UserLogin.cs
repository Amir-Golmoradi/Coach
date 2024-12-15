using System.ComponentModel.DataAnnotations;

namespace Coach.Models.Authentication;

public class UserLogin
{
    [Required] public string UsernameOrEmail { get; init; }

    [Required] [StringLength(128)] public string Password { get; init; }

    public UserLogin(string usernameOrEmail, string password)
    {
        UsernameOrEmail = usernameOrEmail;
        Password = password;
    }
}