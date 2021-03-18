package com.nagpal.shivam.workout_manager.models

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Section : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    val workout: Workout? = null
    val name: String? = null
    val repetitions: Int? = null
    val restingInfo: String? = null
    val order: Int? = null
}