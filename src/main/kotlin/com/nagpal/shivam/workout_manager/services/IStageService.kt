package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.StageWorkoutResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface IStageService {
    fun saveStage(stageRequestDto: StageRequestDto): StageResponseDto
    fun linkWorkout(stageWorkoutRequestDto: StageWorkoutRequestDto): StageWorkoutResponseDto
    fun getStageById(id: Long): StageResponseDto
    fun getStages(page: Int, size: Int): List<StageResponseDto>
    fun getStagesInProgram(programId: Long): List<StageResponseDto>
    fun reorderStages(programId: Long, reorderRequestDto: ReorderRequestDto): List<StageResponseDto>
}
