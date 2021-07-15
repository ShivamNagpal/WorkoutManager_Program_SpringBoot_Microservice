package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.ProgramTransformer
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.repositories.ProgramRepository
import com.nagpal.shivam.workout_manager.services.IProgramService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProgramService @Autowired constructor(
    private val programRepository: ProgramRepository,
    private val programTransformer: ProgramTransformer,
) : IProgramService {
    override fun saveProgram(programRequestDto: ProgramRequestDto): ProgramResponseDto {
        var program = try {
            programTransformer.convertProgramRequestDtoToProgram(programRequestDto)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.invalidWorkoutLevel(programRequestDto.level!!)
            )
        }
        program = programRepository.save(program)
        return programTransformer.convertProgramToProgramResponseDto(program)
    }

    override fun getProgramById(id: String): ProgramResponseDto {
        val programOptional = programRepository.findByUuid(UUID.fromString(id))
        if (programOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.PROGRAM_UUID_NOT_FOUND)
        }
        return programTransformer.convertProgramToProgramResponseDto(programOptional.get())
    }
}
