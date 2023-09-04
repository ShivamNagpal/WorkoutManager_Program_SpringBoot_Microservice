package com.nagpal.shivam.workout.manager.exceptions

import org.springframework.http.HttpStatus

data class ResponseException(val httpStatus: HttpStatus, override val message: String, val payload: Any? = null) :
    RuntimeException(message)
