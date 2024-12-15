using System.ComponentModel.DataAnnotations;

namespace Coach.Models.Workouts;

public readonly struct Workout
{
    [Required] public string Id { get; init; } // Workout ID for the given user

    [Required] public string UserId { get; init; }

    [Required] public string Name { get; init; }

    [Required] public string Description { get; init; }

    [Required] public DateTimeOffset StartTime { get; init; }

    [Required] public DateTimeOffset EndTime { get; init; }

    [Required] public ICollection<Exercise> Exercises { get; init; }
}