package com.nagpal.shivam.workout.manager.filters

import com.nagpal.shivam.workout.manager.utils.Constants
import com.nagpal.shivam.workout.manager.utils.FilterOrderingConstants
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Instant
import java.util.UUID

@Component
@Order(value = FilterOrderingConstants.REQUEST_ATTRIBUTION_FILTER_ORDER)
class RequestAttributionFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        request.setAttribute(Constants.REQUEST_ID, UUID.randomUUID().toString())
        request.setAttribute(Constants.REQUEST_ARRIVAL_TIME, Instant.now().toEpochMilli())
        filterChain.doFilter(request, response)
    }
}
