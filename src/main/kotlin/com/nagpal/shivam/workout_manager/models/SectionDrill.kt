package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.dtos.request.DrillDeepSaveRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout_manager.enums.DrillLengthUnits
import javax.persistence.*

@Entity
class SectionDrill() : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    var section: Section? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drill_id")
    var drill: Drill? = null

    @Column(name = "section_id", insertable = false, updatable = false)
    var sectionId: Long? = null

    @Column(name = "drill_id", insertable = false, updatable = false)
    var drillId: Long? = null
    var length: Long? = null

    @Enumerated(EnumType.STRING)
    var units: DrillLengthUnits? = null

    var description: String? = null

    @Column(name = "\"order\"")
    var order: Int? = null

    constructor(sectionDrillRequestDto: SectionDrillRequestDto, section: Section, drill: Drill) : this() {
        this.section = section
        this.drill = drill
        this.sectionId = section.id
        this.drillId = drill.id
        this.length = sectionDrillRequestDto.length
        this.units = DrillLengthUnits.valueOf(sectionDrillRequestDto.units!!.toUpperCase())
        this.description = sectionDrillRequestDto.description
    }

    constructor(
        drillDeepSaveRequestDto: DrillDeepSaveRequestDto,
        section: Section,
        drill: Drill
    ) : this() {
        this.section = section
        this.drill = drill
        this.length = drillDeepSaveRequestDto.length
        this.units = DrillLengthUnits.valueOf(drillDeepSaveRequestDto.units!!.toUpperCase())
        this.description = drillDeepSaveRequestDto.description
    }

    fun copyForeignKeyIdsToTheFields() {
        this.sectionId = this.section!!.id
        this.drillId = this.drill!!.id
    }
}
