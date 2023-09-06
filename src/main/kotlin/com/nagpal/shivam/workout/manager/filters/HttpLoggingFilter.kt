package com.nagpal.shivam.workout.manager.filters

import com.nagpal.shivam.workout.manager.utils.Constants
import com.nagpal.shivam.workout.manager.utils.FilterOrderingConstants
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(value = FilterOrderingConstants.HTTP_LOGGING_FILTER_ORDER)
class HttpLoggingFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        MDC.put(Constants.REQUEST_ID, request.getAttribute(Constants.REQUEST_ID) as String)
        filterChain.doFilter(request, response)
        log.info("Request to {}: {} - {}", request.method, formURI(request), response.status)
        MDC.remove(Constants.REQUEST_ID)
    }

    private fun formURI(request: HttpServletRequest): String {
        val queryString = request.queryString
        return request.requestURI + if (queryString == null) "" else "?$queryString"
    }
}
