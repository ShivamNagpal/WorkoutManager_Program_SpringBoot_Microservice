package com.nagpal.shivam.workout.manager.controllers

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout.manager.dtos.response.StageWorkoutResponseDto
import com.nagpal.shivam.workout.manager.services.IStageService
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
@RequestMapping("/stage")
class StageController @Autowired constructor(
    private val stageService: IStageService,
) {
    @PostMapping
    fun saveStage(
        @RequestBody @Valid stageRequestDto: StageRequestDto,
    ): ResponseEntity<ResponseWrapper<StageResponseDto>> {
        val stageResponseDto: StageResponseDto = stageService.saveStage(stageRequestDto)
        return ResponseEntity.ok(ResponseWrapper(stageResponseDto))
    }

    @PostMapping("/link-workout")
    fun linkWorkout(
        @RequestBody @Valid stageWorkoutRequestDto: StageWorkoutRequestDto,
    ): ResponseEntity<ResponseWrapper<StageWorkoutResponseDto>> {
        val stageWorkoutResponseDto = stageService.linkWorkout(stageWorkoutRequestDto)
        return ResponseEntity.ok(ResponseWrapper(stageWorkoutResponseDto))
    }

    @GetMapping
    fun getStages(
        @RequestParam(name = Constants.PAGE, defaultValue = Constants.PAGE_DEFAULT_VALUE) page: Int,
        @RequestParam(name = Constants.PAGE_SIZE, defaultValue = Constants.PAGE_SIZE_DEFAULT_VALUE) size: Int,
    ): ResponseEntity<ResponseWrapper<List<StageResponseDto>>> {
        val stages = stageService.getStages(page, size)
        return ResponseEntity.ok(ResponseWrapper(stages))
    }

    @GetMapping("/{id}")
    fun getStageById(@PathVariable id: Long): ResponseEntity<ResponseWrapper<StageResponseDto>> {
        val stageResponseDto = stageService.getStageById(id)
        return ResponseEntity.ok(ResponseWrapper(stageResponseDto))
    }

    @GetMapping("/program/{programId}")
    fun getStagesInProgram(
        @PathVariable("programId") programId: Long,
    ): ResponseEntity<ResponseWrapper<List<StageResponseDto>>> {
        val stages = stageService.getStagesInProgram(programId)
        return ResponseEntity.ok(ResponseWrapper(stages))
    }

    @PostMapping("/reorder/{programId}")
    fun reorderStages(
        @PathVariable("programId") programId: Long,
        @RequestBody @Valid reorderRequestDto: ReorderRequestDto,
    ): ResponseEntity<ResponseWrapper<List<StageResponseDto>>> {
        val stages = stageService.reorderStages(programId, reorderRequestDto)
        return ResponseEntity.ok(ResponseWrapper(stages))
    }
}
