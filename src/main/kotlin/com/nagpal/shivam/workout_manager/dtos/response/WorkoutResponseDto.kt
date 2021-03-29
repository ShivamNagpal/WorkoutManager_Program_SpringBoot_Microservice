package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.nagpal.shivam.workout_manager.models.Workout

data class WorkoutResponseDto(
    val uuid: String?,
    val name: String?,
    val level: String?,
    val description: String?,
    val equipments: String?,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var sections: List<SectionResponseDto>? = null,
) {
    constructor(workout: Workout) : this(
        workout.uuid.toString(),
        workout.name,
        workout.level.toString(),
        workout.description,
        workout.equipments
    )
}
