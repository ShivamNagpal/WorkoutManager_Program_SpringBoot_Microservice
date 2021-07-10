package com.nagpal.shivam.workout_manager.exceptions.handlers

import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class BindExceptionHandler {
    @ExceptionHandler(value = [BindException::class])
    fun handleValidationException(
        servletRequest: HttpServletRequest,
        exception: BindException
    ): ResponseEntity<ResponseWrapper<Any>> {
        val errors = exception.bindingResult.allErrors.associate { (it as FieldError).field to it.defaultMessage }
        return ResponseEntity.badRequest()
            .body(ResponseWrapper(errors, ErrorMessages.ERROR_VALIDATING_THE_FIELDS, false))
    }
}
