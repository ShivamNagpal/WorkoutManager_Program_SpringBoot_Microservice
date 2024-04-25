package com.nagpal.shivam.workout.manager.filters

import com.nagpal.shivam.workout.manager.utils.Constants
import com.nagpal.shivam.workout.manager.utils.FilterOrderingConstants
import com.nagpal.shivam.workout.manager.utils.Slf4jUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
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
        Slf4jUtils.mdcPutMulti(
            mapOf(
                Pair(Constants.REQUEST_ID, request.getAttribute(Constants.REQUEST_ID) as String),
                Pair(Constants.HTTP_METHOD, request.method),
                Pair(Constants.PATH, formURI(request)),
            ),
        )
            .use {
                log.info("HTTP Request received")
                filterChain.doFilter(request, response)
                Slf4jUtils.mdcPut(Constants.HTTP_STATUS, response.status.toString()).use {
                    log.info("HTTP Request responded")
                }
            }
    }

    private fun formURI(request: HttpServletRequest): String {
        val queryString = request.queryString
        return request.requestURI + if (queryString == null) "" else "?$queryString"
    }
}
