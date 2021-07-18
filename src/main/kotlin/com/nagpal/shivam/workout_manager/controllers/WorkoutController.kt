package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/workout")
class WorkoutController @Autowired constructor(
    private val workoutService: IWorkoutService
) {

    @PostMapping
    fun saveWorkout(
        @RequestBody @Valid workoutRequestDto: WorkoutRequestDto,
        @RequestParam(name = "deepSave", defaultValue = "false") deepSave: Boolean
    ): ResponseEntity<ResponseWrapper<WorkoutResponseDto>> {
        val workoutResponseDto = workoutService.saveWorkout(workoutRequestDto, deepSave)
        return ResponseEntity.ok(ResponseWrapper(workoutResponseDto))
    }

    @GetMapping
    fun getWorkouts(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int
    ): ResponseEntity<ResponseWrapper<List<WorkoutResponseDto>>> {
        val workouts = workoutService.getWorkouts(page, size)
        return ResponseEntity.ok(ResponseWrapper(workouts))
    }

    @GetMapping("/{id}")
    fun getWorkoutById(
        @PathVariable id: Long,
        @RequestParam(name = "deepFetch", defaultValue = "false") deepFetch: Boolean
    ): ResponseEntity<ResponseWrapper<WorkoutResponseDto>> {
        val workoutResponseDto = workoutService.getWorkoutById(id, deepFetch)
        return ResponseEntity.ok(ResponseWrapper(workoutResponseDto))
    }
}
