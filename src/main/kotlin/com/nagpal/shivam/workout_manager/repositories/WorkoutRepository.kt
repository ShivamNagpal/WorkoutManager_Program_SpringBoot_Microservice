package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface WorkoutRepository : CrudRepository<Workout, Long> {
    fun findByIdAndDeleted(id: Long, deleted: Boolean = false): Optional<Workout>
    fun findAll(pageable: Pageable): Page<Workout>

    @Query(
        "select w from Workout w, StageWorkout sw " +
                "where sw.workoutId = w.id and sw.stageId = :stageId and " +
                "sw.deleted = false and w.deleted = false order by sw.order"
    )
    fun findAllInAStage(stageId: Long): List<Workout>
}
