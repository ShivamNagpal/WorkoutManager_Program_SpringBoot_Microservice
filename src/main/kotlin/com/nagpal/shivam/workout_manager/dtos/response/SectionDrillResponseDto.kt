package com.nagpal.shivam.workout_manager.dtos.response

import com.nagpal.shivam.workout_manager.models.SectionDrill
import java.util.*

data class SectionDrillResponseDto(
    val uuid: String? = null,
    val sectionId: String? = null,
    val drillId: String? = null,
    val length: Long? = null,
    val units: String? = null,
    var description: String? = null,
) {
    constructor(sectionDrill: SectionDrill, sectionUuid: UUID?, drillUUID: UUID?) : this(
        sectionDrill.uuid.toString(),
        sectionUuid?.toString(),
        drillUUID?.toString(),
        sectionDrill.length,
        sectionDrill.units.toString(),
        sectionDrill.description,
    )
}
