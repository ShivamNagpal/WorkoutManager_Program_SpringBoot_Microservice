package com.nagpal.shivam.workout_manager.models

import javax.persistence.*

@Entity
class StageWorkout : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    var stage: Stage? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    var workout: Workout? = null

    @Column(name = "stage_id", insertable = false, updatable = false)
    var stageId: Long? = null

    @Column(name = "workout_id", insertable = false, updatable = false)
    var workoutId: Long? = null

    @Column(name = "\"order\"")
    var order: Int? = null
}
