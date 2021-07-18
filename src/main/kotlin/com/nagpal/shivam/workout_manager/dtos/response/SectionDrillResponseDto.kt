package com.nagpal.shivam.workout_manager.dtos.response

data class SectionDrillResponseDto(
    val uuid: Long? = null,
    val sectionId: Long? = null,
    val drillId: Long? = null,
    val length: Long? = null,
    val units: String? = null,
    var description: String? = null,
)
