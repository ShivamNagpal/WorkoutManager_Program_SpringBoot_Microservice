package com.nagpal.shivam.workout_manager.models

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
    var length: Long? = null

    @Enumerated(EnumType.STRING)
    var units: DrillLengthUnits? = null

    @Column(name = "\"order\"")
    var order: Int? = null

    constructor(sectionDrillRequestDto: SectionDrillRequestDto, section: Section, drill: Drill) : this() {
        this.section = section
        this.drill = drill
        this.length = sectionDrillRequestDto.length
        this.units = DrillLengthUnits.valueOf(sectionDrillRequestDto.units!!.toUpperCase())
    }
}
