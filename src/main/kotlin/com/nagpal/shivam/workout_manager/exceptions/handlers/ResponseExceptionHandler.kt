package com.nagpal.shivam.workout_manager.exceptions.handlers

import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResponseExceptionHandler {
    @ExceptionHandler(value = [ResponseException::class])
    fun handleResponseException(
        request: HttpServletRequest,
        exception: ResponseException
    ): ResponseEntity<ResponseWrapper<Any>> {
        return ResponseEntity(ResponseWrapper(exception.payload as Any, exception.message, false), exception.httpStatus)
    }
}
