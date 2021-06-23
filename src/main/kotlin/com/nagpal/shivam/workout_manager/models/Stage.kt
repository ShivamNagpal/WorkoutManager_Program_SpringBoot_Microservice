package com.nagpal.shivam.workout_manager.models

import javax.persistence.*

@Entity
class Stage : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    var program: Program? = null

    @Column(name = "program_id", insertable = false, updatable = false)
    var programId: Long? = null

    var name: String? = null
    var description: String? = null

    @Column(name = "\"order\"")
    var order: Int? = null
}
