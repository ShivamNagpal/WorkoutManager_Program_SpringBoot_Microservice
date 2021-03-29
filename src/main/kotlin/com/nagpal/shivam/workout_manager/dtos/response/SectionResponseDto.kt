package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.nagpal.shivam.workout_manager.models.Section

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
) {
    constructor(section: Section) : this(
        section.uuid.toString(),
        section.workout!!.uuid.toString(),
        section.name,
        section.repetitions,
        section.restingInfo,
        section.order
    )
}
