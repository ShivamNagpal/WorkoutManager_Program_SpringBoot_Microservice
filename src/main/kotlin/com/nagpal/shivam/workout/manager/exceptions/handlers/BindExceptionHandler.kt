package com.nagpal.shivam.workout.manager.exceptions.handlers

import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.utils.ErrorMessages
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BindExceptionHandler {
    @ExceptionHandler(value = [BindException::class])
    fun handleValidationException(
        servletRequest: HttpServletRequest,
        exception: BindException,
    ): ResponseEntity<ResponseWrapper<Any>> {
        val errors = exception.bindingResult.allErrors.associate { (it as FieldError).field to it.defaultMessage }
        return ResponseEntity.badRequest()
            .body(ResponseWrapper(errors, ErrorMessages.ERROR_VALIDATING_THE_FIELDS, false))
    }
}
