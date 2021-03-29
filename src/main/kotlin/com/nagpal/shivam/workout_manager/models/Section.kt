package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import javax.persistence.*

@Entity
class Section() : BaseModel() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    var workout: Workout? = null

    @Column(name = "workout_id", insertable = false, updatable = false)
    var workoutId: Long? = null
    var name: String? = null
    var repetitions: Int? = null
    var restingInfo: String? = null

    @Column(name = "\"order\"")
    var order: Int? = null

    constructor(sectionRequestDto: SectionRequestDto, workout: Workout) : this() {
        this.workout = workout
        this.workoutId = workout.id
        this.name = sectionRequestDto.name
        this.repetitions = sectionRequestDto.repetitions
        this.restingInfo = sectionRequestDto.restingInfo
    }
}
