using System.ComponentModel.DataAnnotations;

namespace Coach.Models.Workouts;

public readonly struct Exercise
{
    [Required] public string Name { get; init; }

    [Required] [Range(1, int.MaxValue)] public int Reps { get; init; }

    [Required] [Range(1, int.MaxValue)] public int Sets { get; init; }

    [Required] [Range(0, int.MaxValue)] public int Weight { get; init; }

    [Required] [Range(0, int.MaxValue)] public int Calories { get; init; }
}