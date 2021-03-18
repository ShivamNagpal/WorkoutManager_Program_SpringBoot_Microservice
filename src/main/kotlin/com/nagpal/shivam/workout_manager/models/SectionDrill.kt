package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.enums.DrillLengthUnits
import javax.persistence.*

@Entity
class SectionDrill : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    val section: Section? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drill_id")
    val drill: Drill? = null
    val length: Long? = null

    @Enumerated(EnumType.STRING)
    val units: DrillLengthUnits? = null
}
