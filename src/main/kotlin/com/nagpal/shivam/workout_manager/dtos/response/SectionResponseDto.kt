package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.nagpal.shivam.workout_manager.models.Section
import java.util.*

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
    constructor(section: Section, workoutUuid: UUID?) : this(
        section.uuid.toString(),
        workoutUuid?.toString(),
        section.name,
        section.repetitions,
        section.restingInfo,
        section.order
    )
}
