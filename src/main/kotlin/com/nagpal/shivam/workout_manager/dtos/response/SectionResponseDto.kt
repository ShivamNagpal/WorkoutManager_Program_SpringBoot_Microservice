package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude

data class SectionResponseDto(
    val uuid: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var workoutId: String?,
    val name: String? = null,
    val repetitions: Int? = null,
    val restingInfo: String? = null,
    val order: Int? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var drills: List<DrillResponseDto>? = null,
)
