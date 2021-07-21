package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface WorkoutRepository : CrudRepository<Workout, Long> {
    fun findByIdAndDeleted(id: Long, deleted: Boolean = false): Optional<Workout>
    fun findAll(pageable: Pageable): Page<Workout>
    fun findAllByIdIn(ids: List<Long>): List<Workout>
}
