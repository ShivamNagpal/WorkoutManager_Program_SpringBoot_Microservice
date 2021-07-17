package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.StageWorkoutResponseDto
import com.nagpal.shivam.workout_manager.services.IStageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/stage")
class StageController @Autowired constructor(
    private val stageService: IStageService
) {
    @PostMapping
    fun saveStage(@RequestBody @Valid stageRequestDto: StageRequestDto): ResponseEntity<ResponseWrapper<StageResponseDto>> {
        val stageResponseDto: StageResponseDto = stageService.saveStage(stageRequestDto)
        return ResponseEntity.ok(ResponseWrapper(stageResponseDto))
    }

    @PostMapping("/link-workout")
    fun linkWorkout(
        @RequestBody @Valid stageWorkoutRequestDto: StageWorkoutRequestDto
    ): ResponseEntity<ResponseWrapper<StageWorkoutResponseDto>> {
        val stageWorkoutResponseDto = stageService.linkWorkout(stageWorkoutRequestDto)
        return ResponseEntity.ok(ResponseWrapper(stageWorkoutResponseDto))
    }

    @GetMapping("/{id}")
    fun getStageById(@PathVariable id: String): ResponseEntity<ResponseWrapper<StageResponseDto>> {
        val stageResponseDto = stageService.getStageById(id)
        return ResponseEntity.ok(ResponseWrapper(stageResponseDto))
    }
}
