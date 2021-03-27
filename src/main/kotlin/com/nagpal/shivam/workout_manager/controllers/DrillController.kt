package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.services.impl.DrillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/drill")
class DrillController @Autowired constructor(
    private val drillService: DrillService
) {

    @PostMapping
    fun saveDrill(@RequestBody @Valid drillRequestDto: DrillRequestDto): ResponseEntity<ResponseWrapper<DrillResponseDto>> {
        val drillResponseDto = drillService.saveDrill(drillRequestDto)
        return ResponseEntity.ok(ResponseWrapper(drillResponseDto))
    }
}
