package com.nagpal.shivam.workout_manager.dtos.response

import com.nagpal.shivam.workout_manager.models.Section

data class SectionResponseDto(
    val uuid: String? = null,
    val name: String? = null,
    val repetitions: Int? = null,
    val restingInfo: String? = null,
    val order: Int? = null
) {
    constructor(section: Section) : this(
        section.uuid.toString(),
        section.name,
        section.repetitions,
        section.restingInfo,
        section.order
    )
}
