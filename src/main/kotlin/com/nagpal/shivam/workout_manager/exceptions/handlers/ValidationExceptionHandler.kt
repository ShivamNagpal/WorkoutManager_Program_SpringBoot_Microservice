package com.nagpal.shivam.workout_manager.exceptions.handlers

import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ValidationExceptionHandler {
    fun handleValidationException(
        servletRequest: HttpServletRequest,
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ResponseWrapper<Any>> {
        val errors = exception.bindingResult.allErrors
            .map { (it as FieldError).field to it.defaultMessage }
            .toMap()
        return ResponseEntity.badRequest()
            .body(ResponseWrapper(errors, ErrorMessages.ERROR_VALIDATING_THE_FIELDS, false))
    }
}
