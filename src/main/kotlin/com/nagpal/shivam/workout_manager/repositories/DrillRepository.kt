package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Drill
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DrillRepository : CrudRepository<Drill, Long> {
    fun findByIdAndDeleted(id: Long, deleted: Boolean = false): Optional<Drill>

    @Query(value = "select d from Drill d where d.name in (:names)")
    fun findByNames(names: Iterable<String>): List<Drill>

    @Query(
        "select d from Drill d, SectionDrill sd " +
                "where sd.drillId = d.id and sd.sectionId = :sectionId and " +
                "sd.deleted = false and d.deleted = false order by sd.order"
    )
    fun findAllInASection(sectionId: Long): List<Drill>
}
