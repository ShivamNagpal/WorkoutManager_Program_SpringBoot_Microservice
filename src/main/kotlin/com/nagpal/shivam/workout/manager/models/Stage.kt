package com.nagpal.shivam.workout.manager.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Stage : OrderedBaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    var program: Program? = null

    @Column(name = "program_id", insertable = false, updatable = false)
    var programId: Long? = null

    var name: String? = null
    var description: String? = null
}
