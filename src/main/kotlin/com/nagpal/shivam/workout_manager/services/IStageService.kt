package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto

interface IStageService {
    fun saveStage(stageRequestDto: StageRequestDto): StageResponseDto
}
