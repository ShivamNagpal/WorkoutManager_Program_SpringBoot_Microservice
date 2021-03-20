package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Workout() : BaseModel() {
    var name: String? = null

    @Enumerated(EnumType.STRING)
    var level: WorkoutLevel? = null
    var description: String? = null
    var equipments: String? = null

    constructor(workoutRequestDto: WorkoutRequestDto) : this() {
        this.name = workoutRequestDto.name
        // TODO: Handle the exception if workout level is invalid enum
        this.level = WorkoutLevel.valueOf(workoutRequestDto.level.toUpperCase())
        this.description = workoutRequestDto.description
        this.equipments = workoutRequestDto.equipments
    }
}
