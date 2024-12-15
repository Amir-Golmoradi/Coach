using System.ComponentModel.DataAnnotations;

namespace Coach.Models.Authentication;

public class UserRegistration
{
    [Required]
    [StringLength(32, MinimumLength = 1)]
    public string Username { get; init; }

    [Required]
    [EmailAddress(ErrorMessage = "Invalid email address.")]
    [RegularExpression(
        @"^[\w!#$%&'*+\-/=?\^_`{|}~]+(\.[\w!#$%&'*+\-/=?\^_`{|}~]+)*" + "@" +
        @"((([\-\w]+\.)+[a-zA-Z]{2,4})|(([0-9]{1,3}\.){3}[0-9]{1,3}))$", ErrorMessage = "Invalid email format.")]
    public string Email { get; init; }

    [Required]
    [StringLength(128, MinimumLength = 8)]
    public string Password { get; init; }

    public UserRegistration(string username, string email, string password)
    {
        Username = username;
        Email = email;
        Password = password;
    }
}