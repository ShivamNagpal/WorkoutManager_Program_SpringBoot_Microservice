package com.nagpal.shivam.workout_manager.dtos.response

data class SectionDrillResponseDto(
    val uuid: String? = null,
    val sectionId: String? = null,
    val drillId: String? = null,
    val length: Long? = null,
    val units: String? = null,
    var description: String? = null,
)
