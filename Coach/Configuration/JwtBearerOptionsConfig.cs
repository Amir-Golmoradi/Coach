namespace Coach.Configuration;

public class JwtBearerOptionsConfig
{
    public string? Audience { get; set; }
    public string? Issuer { get; set; }
    public string? Secret { get; set; }
}