package com.nagpal.shivam.workout.manager.exceptions.handlers

import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.exceptions.ResponseException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResponseExceptionHandler {
    @ExceptionHandler(value = [ResponseException::class])
    fun handleResponseException(
        request: HttpServletRequest,
        exception: ResponseException,
    ): ResponseEntity<ResponseWrapper<Any?>> {
        return ResponseEntity(ResponseWrapper(exception.payload, exception.message, false), exception.httpStatus)
    }
}
