package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.ProgramTransformer
import com.nagpal.shivam.workout_manager.enums.ResponseMessage
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.repositories.ProgramRepository
import com.nagpal.shivam.workout_manager.services.IProgramService
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
            val responseMessage = ResponseMessage.INVALID_WORKOUT_LEVEL
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                responseMessage.messageCode,
                responseMessage.getMessage(programRequestDto.level!!)
            )
        }
        program = programRepository.save(program)
        return programTransformer.convertProgramToProgramResponseDto(program)
    }

    override fun getProgramById(id: Long): ProgramResponseDto {
        val programOptional = programRepository.findByIdAndDeleted(id)
        if (programOptional.isEmpty) {
            val responseMessage = ResponseMessage.PROGRAM_UUID_NOT_FOUND
            throw ResponseException(HttpStatus.BAD_REQUEST, responseMessage.messageCode, responseMessage.getMessage())
        }
        return programTransformer.convertProgramToProgramResponseDto(programOptional.get())
    }

    override fun getPrograms(page: Int, pageSize: Int): List<ProgramResponseDto> {
        val pageRequest: PageRequest = PageRequest.of(page, pageSize)
        val programs = programRepository.findAllByDeleted(pageRequest)
        return programs.map { programTransformer.convertProgramToProgramResponseDto(it) }
    }
}
