package com.nagpal.shivam.workout_manager.dtos.response

import com.nagpal.shivam.workout_manager.models.Workout

data class WorkoutResponseDto(
    val uuid: String?,
    val name: String?,
    val level: String?,
    val description: String?,
    val equipments: String?
) {
    constructor(workout: Workout) : this(
        workout.uuid.toString(),
        workout.name,
        workout.level.toString(),
        workout.description,
        workout.equipments
    )
}
