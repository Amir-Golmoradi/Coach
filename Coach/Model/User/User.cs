namespace Coach.Model.User;

public class User
{
    public string Id { get; init; }
    public string Username { get; init; }
    public string Email { get; init; }
    public string PasswordHash { get; init; }

    public User(string id, string username, string email, string passwordHash)
    {
        Id = id;
        Username = username;
        Email = email;
        PasswordHash = passwordHash;
    }
}