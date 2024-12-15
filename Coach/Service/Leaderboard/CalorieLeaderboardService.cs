using Coach.Models.Workouts;
using Coach.Services.Workouts;
using StackExchange.Redis;

namespace Coach.Services.Leaderboard;

public class CalorieLeaderboardService
{
    private readonly IDatabase _redis;
    private readonly WorkoutService _workoutService;

    public CalorieLeaderboardService(IDatabase redis, WorkoutService workoutService)
    {
        _redis = redis;

        _workoutService = workoutService;
        _workoutService.OnWorkoutUpdated += UpdateCalorieLeaderboard;
    }

    private const string CalorieLeaderboardKey = $"leaderboards:calories";

    public async Task<List<Dictionary<string, object>>> GetCalorieLeaderboardRange(int start, int stop)
    {
        var query = await _redis.SortedSetRangeByRankWithScoresAsync(CalorieLeaderboardKey, start, stop,
            Order.Descending);

        List<Dictionary<string, object>> top100 = new();

        foreach (var entry in query)
            top100.Add(new Dictionary<string, object>
            {
                { "userId", entry.Element.ToString() },
                { "calories", (int)entry.Score }
            });

        return top100;
    }

    private async Task UpdateCalorieLeaderboard(Workout? oldWorkout, Workout? newWorkout, string userId)
    {
        var caloriesDiff = 0;

        if (oldWorkout is not null) caloriesDiff -= SumCalories((Workout)oldWorkout);

        if (newWorkout is not null) caloriesDiff += SumCalories((Workout)newWorkout);

        await _redis.SortedSetIncrementAsync(CalorieLeaderboardKey, userId, caloriesDiff);
    }

    private static int SumCalories(Workout workout)
    {
        return workout.Exercises.Sum(e => e.Calories);
    }
}