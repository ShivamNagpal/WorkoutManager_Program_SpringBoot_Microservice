package com.nagpal.shivam.workout_manager.models

import jakarta.persistence.*

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
