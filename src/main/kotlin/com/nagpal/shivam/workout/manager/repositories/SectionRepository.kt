package com.nagpal.shivam.workout.manager.repositories

import com.nagpal.shivam.workout.manager.models.Section
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SectionRepository : CrudRepository<Section, Long> {
    @Query(value = "select max(s.order) from Section s where s.workout.id = :workoutId")
    fun fetchMaxCount(workoutId: Long): Optional<Int>
    fun findByIdAndDeleted(id: Long, deleted: Boolean = false): Optional<Section>

    @Query(
        value = "select s from Section s " +
            "where s.workout.id = :workoutId and s.deleted = false " +
            "order by s.order",
    )
    fun findByWorkoutId(workoutId: Long): List<Section>
}
