package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.data.repository.CrudRepository

interface WorkoutRepository : CrudRepository<Workout, Long> {
}
