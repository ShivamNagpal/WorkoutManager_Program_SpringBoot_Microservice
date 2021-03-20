package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/workout")

class WorkoutController @Autowired constructor(
    private val workoutService: IWorkoutService
) {

    @PostMapping
    fun saveWorkout(@Valid workoutRequestDto: WorkoutRequestDto): ResponseEntity<ResponseWrapper<WorkoutResponseDto>> {
        val workoutResponseDto = workoutService.saveWorkout(workoutRequestDto)
        return ResponseEntity.ok(ResponseWrapper(workoutResponseDto))
    }
}
