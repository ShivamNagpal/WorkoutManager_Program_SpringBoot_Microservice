package com.nagpal.shivam.workout.manager.controllers

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.dtos.response.SectionDrillResponseDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout.manager.services.ISectionService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/section")
class SectionController @Autowired constructor(
    private val sectionService: ISectionService,
) {

    @PostMapping
    fun saveSection(
        @RequestBody @Valid sectionRequestDto: SectionRequestDto,
    ): ResponseEntity<ResponseWrapper<SectionResponseDto>> {
        val sectionResponseDto = sectionService.saveSection(sectionRequestDto)
        return ResponseEntity.ok(ResponseWrapper(sectionResponseDto))
    }

    @PostMapping("/link-drill")
    fun linkDrill(
        @RequestBody @Valid sectionDrillRequestDto: SectionDrillRequestDto,
    ): ResponseEntity<ResponseWrapper<SectionDrillResponseDto>> {
        val sectionDrillResponseDto = sectionService.linkWorkout(sectionDrillRequestDto)
        return ResponseEntity.ok(ResponseWrapper(sectionDrillResponseDto))
    }

    @PostMapping("/reorder/{workoutId}")
    fun reorderSections(
        @PathVariable("workoutId") workoutId: Long,
        @RequestBody @Valid reorderRequestDto: ReorderRequestDto,
    ): ResponseEntity<ResponseWrapper<List<SectionResponseDto>>> {
        val sections = sectionService.reorderSections(workoutId, reorderRequestDto)
        return ResponseEntity.ok(ResponseWrapper(sections))
    }
}
