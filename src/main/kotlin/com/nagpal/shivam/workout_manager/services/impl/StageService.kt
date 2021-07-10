package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.StageTransformer
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.repositories.ProgramRepository
import com.nagpal.shivam.workout_manager.repositories.StageRepository
import com.nagpal.shivam.workout_manager.services.IStageService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class StageService @Autowired constructor(
    private val stageRepository: StageRepository,
    private val stageTransformer: StageTransformer,
    private val programRepository: ProgramRepository,
) : IStageService {
    override fun saveStage(stageRequestDto: StageRequestDto): StageResponseDto {
        val programOptional = programRepository.findByUuid(UUID.fromString(stageRequestDto.programId))
        if (programOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.PROGRAM_UUID_NOT_FOUND)
        }
        val program = programOptional.get()
        var stage = stageTransformer.convertStageRequestDtoToStage(stageRequestDto, program)
        val maxCount = stageRepository.fetchMaxCount(stage.programId!!).orElse(0)
        stage.order = maxCount + 1
        stage = stageRepository.save(stage)
        return stageTransformer.convertStageToStageResponseDto(stage, program.uuid)
    }
}
