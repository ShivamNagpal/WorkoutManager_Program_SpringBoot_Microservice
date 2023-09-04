package com.nagpal.shivam.workout.manager.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.exceptions.ResponseException
import com.nagpal.shivam.workout.manager.helpers.impl.JwtHelper
import com.nagpal.shivam.workout.manager.utils.Constants
import com.nagpal.shivam.workout.manager.utils.ErrorMessages
import io.netty.handler.codec.http.HttpResponseStatus
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtAuthenticationHandler @Autowired constructor(private val jwtHelper: JwtHelper) : AuthenticationEntryPoint {
    private val objectMapper: ObjectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?,
    ) {
        response.setHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
        try {
            val authorizationHeader = request.getHeader(Constants.AUTHORIZATION)
            if (authorizationHeader == null) {
                writeResponse(
                    response,
                    HttpResponseStatus.BAD_REQUEST.code(),
                    ResponseWrapper(null, ErrorMessages.AUTHORIZATION_HEADER_MUST_BE_PRESENT, false),
                )
                return
            }
            jwtHelper.verifyAndDecode(authorizationHeader)
        } catch (responseException: ResponseException) {
            writeResponse(
                response,
                responseException.httpStatus.value(),
                ResponseWrapper(responseException.payload, responseException.message, false),
            )
            return
        }
        writeResponse(
            response,
            HttpResponseStatus.FORBIDDEN.code(),
            ResponseWrapper(null, ErrorMessages.ACCESS_DENIED, false),
        )
    }

    fun <T> writeResponse(response: HttpServletResponse, httpStatus: Int, responseWrapper: ResponseWrapper<T>) {
        response.status = httpStatus
        response.writer.println(objectMapper.writeValueAsString(responseWrapper))
    }
}
