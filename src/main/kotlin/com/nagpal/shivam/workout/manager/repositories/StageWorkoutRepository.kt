package com.nagpal.shivam.workout.manager.repositories

import com.nagpal.shivam.workout.manager.models.StageWorkout
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface StageWorkoutRepository : CrudRepository<StageWorkout, Long> {
    @Query(value = "select max(sw.order) from StageWorkout sw where sw.stage.id = :stageId")
    fun fetchMaxCount(stageId: Long): Optional<Int>
    fun findAllByStageIdAndDeleted(stageId: Long, deleted: Boolean = false): List<StageWorkout>
}
