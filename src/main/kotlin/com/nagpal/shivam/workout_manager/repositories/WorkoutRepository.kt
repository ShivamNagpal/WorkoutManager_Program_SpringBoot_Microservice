package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.data.repository.CrudRepository
import java.util.*

interface WorkoutRepository : CrudRepository<Workout, Long> {
    fun findByUuid(uuid: UUID): Optional<Workout>
}