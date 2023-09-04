package com.nagpal.shivam.workout_manager.models

import jakarta.persistence.*

@Entity
class Section : OrderedBaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    var workout: Workout? = null

    @Column(name = "workout_id", insertable = false, updatable = false)
    var workoutId: Long? = null
    var name: String? = null
    var repetitions: Int? = null
    var restingInfo: String? = null
}
