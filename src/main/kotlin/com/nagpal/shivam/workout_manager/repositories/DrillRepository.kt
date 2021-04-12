package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Drill
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DrillRepository : CrudRepository<Drill, Long> {
    fun findByUuid(uuid: UUID): Optional<Drill>

    @Query(value = "select d from Drill d where d.name in (:names)")
    fun findByNames(names: Iterable<String>): List<Drill>
}
