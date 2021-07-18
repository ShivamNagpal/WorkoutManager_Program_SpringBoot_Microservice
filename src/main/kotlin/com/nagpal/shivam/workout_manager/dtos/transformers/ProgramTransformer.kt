package com.nagpal.shivam.workout_manager.dtos.transformers

import com.nagpal.shivam.workout_manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import com.nagpal.shivam.workout_manager.models.Program
import org.springframework.stereotype.Component

@Component
class ProgramTransformer {
    fun convertProgramRequestDtoToProgram(programRequestDto: ProgramRequestDto): Program {
        val program = Program()
        return program.apply {
            name = programRequestDto.name
            level = WorkoutLevel.valueOf(programRequestDto.level!!.toUpperCase())
            description = programRequestDto.description
            equipments = programRequestDto.equipments
            designedFor = programRequestDto.designedFor
        }
    }

    fun convertProgramToProgramResponseDto(program: Program): ProgramResponseDto {
        return ProgramResponseDto(
            program.id,
            program.name,
            program.level.toString(),
            program.description,
            program.equipments,
            program.designedFor,
        )
    }
}
