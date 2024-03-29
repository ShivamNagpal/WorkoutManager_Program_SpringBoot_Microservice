package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout.manager.models.Program
import com.nagpal.shivam.workout.manager.models.Stage
import org.springframework.stereotype.Component

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

    fun convertStageToStageResponseDto(stage: Stage): StageResponseDto {
        return StageResponseDto(
            stage.id,
            stage.programId,
            stage.name,
            stage.description,
            stage.order,
        )
    }
}
