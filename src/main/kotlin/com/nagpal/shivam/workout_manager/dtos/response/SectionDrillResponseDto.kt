package com.nagpal.shivam.workout_manager.dtos.response

import com.nagpal.shivam.workout_manager.models.SectionDrill

data class SectionDrillResponseDto(
    val uuid: String? = null,
    val sectionId: String? = null,
    val drillId: String? = null,
    val length: Long? = null,
    val units: String? = null,
) {
    constructor(sectionDrill: SectionDrill) : this(
        sectionDrill.uuid.toString(),
        sectionDrill.section!!.uuid.toString(),
        sectionDrill.drill!!.uuid.toString(),
        sectionDrill.length,
        sectionDrill.units.toString(),
    )
}
