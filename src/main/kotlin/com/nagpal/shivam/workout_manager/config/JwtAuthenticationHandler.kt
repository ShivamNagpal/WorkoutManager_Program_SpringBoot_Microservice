package com.nagpal.shivam.workout_manager.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.enums.ResponseMessage
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.helpers.impl.JwtHelper
import com.nagpal.shivam.workout_manager.utils.Constants
import io.netty.handler.codec.http.HttpResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtAuthenticationHandler @Autowired constructor(private val jwtHelper: JwtHelper) : AuthenticationEntryPoint {
    private val objectMapper: ObjectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException?
    ) {
        response.setHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
        try {
            val authorizationHeader = request.getHeader(Constants.AUTHORIZATION)
            if (authorizationHeader == null) {
                val responseMessage = ResponseMessage.AUTHORIZATION_HEADER_MUST_BE_PRESENT
                writeResponse(
                    response,
                    HttpResponseStatus.BAD_REQUEST.code(),
                    ResponseWrapper(null, responseMessage.messageCode, responseMessage.getMessage(), false)
                )
                return
            }
            jwtHelper.verifyAndDecode(authorizationHeader)
        } catch (responseException: ResponseException) {
            writeResponse(
                response,
                responseException.httpStatus.value(),
                ResponseWrapper(
                    responseException.payload,
                    responseException.messageCode,
                    responseException.message,
                    false
                )
            )
            return
        }
        val responseMessage = ResponseMessage.ACCESS_DENIED
        writeResponse(
            response,
            HttpResponseStatus.FORBIDDEN.code(),
            ResponseWrapper(null, responseMessage.messageCode, responseMessage.getMessage(), false)
        )
    }

    fun <T> writeResponse(response: HttpServletResponse, httpStatus: Int, responseWrapper: ResponseWrapper<T>) {
        response.status = httpStatus
        response.writer.println(objectMapper.writeValueAsString(responseWrapper))
    }
}
