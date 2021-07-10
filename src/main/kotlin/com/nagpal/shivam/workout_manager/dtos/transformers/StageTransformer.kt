package com.nagpal.shivam.workout_manager.dtos.transformers

import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout_manager.models.Program
import com.nagpal.shivam.workout_manager.models.Stage
import org.springframework.stereotype.Component
import java.util.*

@Component
class StageTransformer {
    fun convertStageRequestDtoToStage(stageRequestDto: StageRequestDto, program: Program): Stage {
        val stage = Stage()
        return stage.apply {
            this.program = program
            programId = program.id
            name = stageRequestDto.name
            description = stageRequestDto.description
        }
    }

    fun convertStageToStageResponseDto(stage: Stage, programUUID: UUID?): StageResponseDto {
        return StageResponseDto(
            stage.uuid?.toString(),
            programUUID?.toString(),
            stage.name,
            stage.description
        )
    }
}
