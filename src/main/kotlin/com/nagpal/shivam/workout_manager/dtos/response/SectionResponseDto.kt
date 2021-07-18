package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude

data class SectionResponseDto(
    val id: Long? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var workoutId: Long?,
    val name: String? = null,
    val repetitions: Int? = null,
    val restingInfo: String? = null,
    val order: Int? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var drills: List<DrillResponseDto>? = null,
)
