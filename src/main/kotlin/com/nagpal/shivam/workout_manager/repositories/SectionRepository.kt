package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Section
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SectionRepository : CrudRepository<Section, Long> {
    @Query(value = "select max(s.order) from Section s where s.workout.id = :workoutId")
    fun fetchMaxCount(workoutId: Long): Optional<Int>
    fun findByUuid(uuid: UUID): Optional<Section>
}
