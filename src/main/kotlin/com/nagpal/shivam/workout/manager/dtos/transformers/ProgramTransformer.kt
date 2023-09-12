package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout.manager.enums.WorkoutLevel
import com.nagpal.shivam.workout.manager.models.Program
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class ProgramTransformer {
    fun convertProgramRequestDtoToProgram(programRequestDto: ProgramRequestDto): Program {
        val program = Program()
        return program.apply {
            name = programRequestDto.name
            level = WorkoutLevel.valueOf(programRequestDto.level!!.uppercase(Locale.getDefault()))
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
