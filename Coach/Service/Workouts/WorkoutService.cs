using Coach.Models.Workouts;
using StackExchange.Redis;
using System.Text.Json;

namespace Coach.Services.Workouts;

public delegate Task OnWorkoutUpdatedDelegate(Workout? oldWorkout, Workout? newWorkout, string userId);

public class WorkoutService
{
    private readonly IDatabase _redis;

    public WorkoutService(IDatabase redis)
    {
        _redis = redis;
    }

    private const string UserWorkoutsIdKey = "workouts:{0}:id"; // {0} - UserId

    private static string WorkoutByIdHashKey(string userId, string workoutId)
    {
        return string.Format("workouts:{0}:{1}", userId, workoutId);
    }

    private async Task<bool> WorkoutExistsAsync(string userId, string workoutId)
    {
        return await _redis.KeyExistsAsync(WorkoutByIdHashKey(userId, workoutId));
    }

    public async Task<Workout> AddWorkoutAsync(string userId, WorkoutInput workout)
    {
        var workoutId = await GetUserNextWorkoutId(userId);

        return await SetWorkoutAsync(userId, workoutId, workout);
    }

    public async Task<Workout?> UpdateWorkoutAsync(string userId, string workoutId, WorkoutInput workout)
    {
        return await WorkoutExistsAsync(userId, workoutId) ? await SetWorkoutAsync(userId, workoutId, workout) : null;
    }

    public event OnWorkoutUpdatedDelegate? OnWorkoutUpdated;

    private async Task InvokeOnWorkoutUpdatedAsync(Workout? oldWorkout, Workout? newWorkout, string userId)
    {
        // CONCURRENTLY await every subscriber-task
        if (OnWorkoutUpdated is not null)
            await Task.WhenAll(OnWorkoutUpdated.GetInvocationList().OfType<OnWorkoutUpdatedDelegate>()
                .Select(h => h(oldWorkout, newWorkout, userId)));
    }

    private async Task<Workout> SetWorkoutAsync(string userId, string workoutId, WorkoutInput workout)
    {
        // Invoke OnWorkoutUpdated event
        var oldWorkout = await GetWorkoutByIdAsync(userId, workoutId);

        var newWorkout = new Workout
        {
            Id = workoutId,
            UserId = userId,
            Name = workout.Name,
            Description = workout.Description,
            StartTime = workout.StartTime,
            EndTime = workout.EndTime,
            Exercises = workout.Exercises
        };

        await InvokeOnWorkoutUpdatedAsync(oldWorkout, newWorkout, userId);

        // Add the hash entries to Redis
        var hashEntries = new HashEntry[]
        {
            new("UserId", userId),
            new("Name", workout.Name),
            new("Description", workout.Description),
            new("StartTime", workout.StartTime.ToString("o")),
            new("EndTime", workout.EndTime.ToString("o")),
            new("Exercises", JsonSerializer.Serialize(workout.Exercises.ToList()))
        };

        await _redis.HashSetAsync(WorkoutByIdHashKey(userId, workoutId), hashEntries);

        return newWorkout;
    }

    public async Task<Workout?> GetWorkoutByIdAsync(string userId, string workoutId)
    {
        if (!await WorkoutExistsAsync(userId, workoutId)) return null;

        // Get the hash key for the workout
        var hashKey = WorkoutByIdHashKey(userId, workoutId);

        // Get all the fields and values of the hash
        var hashEntries = await _redis.HashGetAllAsync(hashKey);

        // Deserialize the Exercises field
        var exercises =
            JsonSerializer.Deserialize<List<Exercise>>(hashEntries.First(x => x.Name == "Exercises").Value!) ??
            new List<Exercise>();

        return new Workout
        {
            Id = workoutId,
            UserId = userId,
            Name = hashEntries.First(x => x.Name == "Name").Value!,
            Description = hashEntries.First(x => x.Name == "Description").Value!,
            StartTime = DateTimeOffset.Parse(hashEntries.First(x => x.Name == "StartTime").Value!),
            EndTime = DateTimeOffset.Parse(hashEntries.First(x => x.Name == "EndTime").Value!),
            Exercises = exercises
        };
    }

    public async Task<List<Workout>> GetWorkoutsInIdRangeAsync(string userId, int from, int to)
    {
        List<Workout> workouts = new();

        var lastWorkoutId = int.Parse(await GetUserLastWorkoutId(userId));

        from = Math.Max(from, 1);
        to = Math.Min(to, lastWorkoutId);

        while (from <= to)
        {
            var workout = await GetWorkoutByIdAsync(userId, from.ToString());

            if (workout is not null) workouts.Add((Workout)workout);

            from++;
        }

        return workouts;
    }

    public async Task<List<Workout>> GetLastWorkoutsAsync(string userId, int amount)
    {
        var lastWorkoutId = int.Parse(await GetUserLastWorkoutId(userId));

        return await GetWorkoutsInIdRangeAsync(userId, lastWorkoutId - amount + 1, lastWorkoutId);
    }

    public async Task<bool> DeleteWorkoutAsync(string userId, string workoutId)
    {
        var oldWorkout = await GetWorkoutByIdAsync(userId, workoutId);

        await InvokeOnWorkoutUpdatedAsync(oldWorkout, null, userId);

        return await _redis.KeyDeleteAsync(WorkoutByIdHashKey(userId, workoutId));
    }

    public async Task<string> GetUserLastWorkoutId(string userId)
    {
        var key = string.Format(UserWorkoutsIdKey, userId);
        string? id = await _redis.StringGetAsync(key);

        return id ?? "-1";
    }

    private async Task<string> GetUserNextWorkoutId(string userId)
    {
        var key = string.Format(UserWorkoutsIdKey, userId);
        var id = await _redis.StringIncrementAsync(key);

        return id.ToString();
    }
}