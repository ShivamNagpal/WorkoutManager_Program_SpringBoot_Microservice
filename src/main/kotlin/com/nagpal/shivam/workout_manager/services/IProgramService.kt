package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ProgramResponseDto
import org.springframework.transaction.annotation.Transactional


@Transactional
interface IProgramService {
    fun saveProgram(programRequestDto: ProgramRequestDto): ProgramResponseDto
    fun getProgramById(id: Long): ProgramResponseDto
    fun getPrograms(page: Int, pageSize: Int): List<ProgramResponseDto>
}
