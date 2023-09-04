package com.nagpal.shivam.workout.manager.controllers

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout.manager.services.IWorkoutService
import com.nagpal.shivam.workout.manager.utils.Constants
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/workout")
class WorkoutController @Autowired constructor(
    private val workoutService: IWorkoutService,
) {

    @PostMapping
    fun saveWorkout(
        @RequestBody @Valid workoutRequestDto: WorkoutRequestDto,
        @RequestParam(name = "deepSave", defaultValue = "false") deepSave: Boolean,
    ): ResponseEntity<ResponseWrapper<WorkoutResponseDto>> {
        val workoutResponseDto = workoutService.saveWorkout(workoutRequestDto, deepSave)
        return ResponseEntity.ok(ResponseWrapper(workoutResponseDto))
    }

    @GetMapping
    fun getWorkouts(
        @RequestParam(name = Constants.PAGE, defaultValue = Constants.PAGE_DEFAULT_VALUE) page: Int,
        @RequestParam(name = Constants.PAGE_SIZE, defaultValue = Constants.PAGE_SIZE_DEFAULT_VALUE) pageSize: Int,
    ): ResponseEntity<ResponseWrapper<List<WorkoutResponseDto>>> {
        val workouts = workoutService.getWorkouts(page, pageSize)
        return ResponseEntity.ok(ResponseWrapper(workouts))
    }

    @GetMapping("/{id}")
    fun getWorkoutById(
        @PathVariable id: Long,
        @RequestParam(name = "deepFetch", defaultValue = "false") deepFetch: Boolean,
    ): ResponseEntity<ResponseWrapper<WorkoutResponseDto>> {
        val workoutResponseDto = workoutService.getWorkoutById(id, deepFetch)
        return ResponseEntity.ok(ResponseWrapper(workoutResponseDto))
    }

    @GetMapping("/stage/{stageId}")
    fun getWorkoutsInStage(
        @PathVariable("stageId") stageId: Long,
    ): ResponseEntity<ResponseWrapper<List<WorkoutResponseDto>>> {
        val workoutsInStage = workoutService.getWorkoutsInStage(stageId)
        return ResponseEntity.ok(ResponseWrapper(workoutsInStage))
    }

    @PostMapping("/reorder/{stageId}")
    fun reorderWorkouts(
        @PathVariable("stageId") stageId: Long,
        @RequestBody @Valid reorderRequestDto: ReorderRequestDto,
    ): ResponseEntity<ResponseWrapper<List<WorkoutResponseDto>>> {
        val workouts = workoutService.reorderWorkouts(stageId, reorderRequestDto)
        return ResponseEntity.ok(ResponseWrapper(workouts))
    }
}
