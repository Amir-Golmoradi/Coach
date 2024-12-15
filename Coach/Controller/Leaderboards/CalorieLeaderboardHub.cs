using Coach.Services.Leaderboard;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.SignalR;

namespace Coach.Controller.Leaderboards;

[Authorize]
public class CalorieLeaderboardHub : Hub
{
    private static int _clientCount = 0;
    private const int IntervalInSeconds = 10;
    private static Timer? _timer;

    private readonly CalorieLeaderboardService _service;
    private readonly IHubContext<CalorieLeaderboardHub> _hubContext;

    public CalorieLeaderboardHub(CalorieLeaderboardService service, IHubContext<CalorieLeaderboardHub> hubContext)
    {
        _service = service;
        _hubContext = hubContext;
    }

    public override async Task OnConnectedAsync()
    {
        _clientCount++;

        if (_clientCount == 1)
            _timer = new Timer(SendMessage, null, TimeSpan.Zero, TimeSpan.FromSeconds(IntervalInSeconds));

        await base.OnConnectedAsync();
    }

    public override async Task OnDisconnectedAsync(Exception? exception)
    {
        _clientCount--;

        if (_clientCount == 0) _timer?.Dispose();

        await base.OnDisconnectedAsync(exception);
    }

    private async void SendMessage(object? state)
    {
        await _hubContext.Clients.All.SendAsync("ReceiveLeaderboard", await _service.GetCalorieLeaderboardRange(0, 99));
    }
}