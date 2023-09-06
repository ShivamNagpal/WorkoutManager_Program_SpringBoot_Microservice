package com.nagpal.shivam.workout.manager.services.impl

import com.nagpal.shivam.workout.manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout.manager.dtos.transformers.ProgramTransformer
import com.nagpal.shivam.workout.manager.exceptions.ResponseException
import com.nagpal.shivam.workout.manager.repositories.ProgramRepository
import com.nagpal.shivam.workout.manager.services.IProgramService
import com.nagpal.shivam.workout.manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

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
                ErrorMessages.invalidWorkoutLevel(programRequestDto.level!!),
            )
        }
        program = programRepository.save(program)
        return programTransformer.convertProgramToProgramResponseDto(program)
    }

    override fun getProgramById(id: Long): ProgramResponseDto {
        val programOptional = programRepository.findByIdAndDeleted(id)
        if (programOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.PROGRAM_UUID_NOT_FOUND)
        }
        return programTransformer.convertProgramToProgramResponseDto(programOptional.get())
    }

    override fun getPrograms(page: Int, pageSize: Int): List<ProgramResponseDto> {
        val pageRequest: PageRequest = PageRequest.of(page, pageSize)
        val programs = programRepository.findAllByDeleted(pageRequest)
        return programs.map { programTransformer.convertProgramToProgramResponseDto(it) }
    }
}
