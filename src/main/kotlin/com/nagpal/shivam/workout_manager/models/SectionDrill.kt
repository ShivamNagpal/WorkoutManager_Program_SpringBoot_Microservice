package com.nagpal.shivam.workout_manager.models

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

    fun copyForeignKeyIdsToTheFields() {
        this.sectionId = this.section!!.id
        this.drillId = this.drill!!.id
    }
}
