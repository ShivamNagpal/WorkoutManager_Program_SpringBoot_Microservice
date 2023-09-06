package com.nagpal.shivam.workout.manager.models

import com.nagpal.shivam.workout.manager.enums.DrillLengthUnits
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class SectionDrill : OrderedBaseModel() {
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

    fun copyForeignKeyIdsToTheFields() {
        this.sectionId = this.section!!.id
        this.drillId = this.drill!!.id
    }
}
