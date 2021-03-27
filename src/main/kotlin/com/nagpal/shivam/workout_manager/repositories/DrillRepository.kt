package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Drill
import org.springframework.data.repository.CrudRepository

interface DrillRepository : CrudRepository<Drill, Long>
