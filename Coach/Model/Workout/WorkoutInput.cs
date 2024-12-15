using System.ComponentModel.DataAnnotations;

namespace Coach.Models.Workouts;

public readonly struct WorkoutInput
{
    [Required] public string Name { get; init; }

    [Required] public string Description { get; init; }

    [Required] public DateTimeOffset StartTime { get; init; }

    [Required] public DateTimeOffset EndTime { get; init; }

    [Required] [MinLength(1)] public ICollection<Exercise> Exercises { get; init; }
}