package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.enums.WorkoutLevel

class Workout : BaseModel() {
    val name: String? = null
    val level: WorkoutLevel? = null
    val description: String? = null
    val equipments: String? = null
    val meta: Map<Any, Any> = mapOf<Any, Any>()
}