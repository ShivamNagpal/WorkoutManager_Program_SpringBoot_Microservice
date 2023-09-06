package com.nagpal.shivam.workout.manager.filters

import com.nagpal.shivam.workout.manager.utils.FilterOrderingConstants
import com.nagpal.shivam.workout.manager.utils.HeaderConstants
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(value = FilterOrderingConstants.CORS_FILTER_ORDER)
class CorsFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        response.apply {
            setHeader(HeaderConstants.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
            setHeader(HeaderConstants.ACCESS_CONTROL_ALLOW_HEADERS, "X-Requested-With, Authorization")
            setHeader(HeaderConstants.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, OPTIONS, DELETE")
            setHeader(HeaderConstants.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HeaderConstants.ORIGIN))
            setHeader(HeaderConstants.ACCESS_CONTROL_MAX_AGE, "3600")
            setHeader(HeaderConstants.CACHE_CONTROL, "no-store, max-age=0")
            setHeader(HeaderConstants.CONTENT_SECURITY_POLICY, "default-src 'none'")
            setHeader(HeaderConstants.CROSS_ORIGIN_EMBEDDER_POLICY, "require-corp")
            setHeader(HeaderConstants.CROSS_ORIGIN_OPENER_POLICY, "same-origin")
            setHeader(HeaderConstants.CROSS_ORIGIN_RESOURCE_POLICY, "same-origin")
            setHeader(HeaderConstants.PERMISSIONS_POLICY, HeaderConstants.PERMISSIONS_POLICY_VALUE)
            setHeader(HeaderConstants.PRAGMA, "no-cache")
            setHeader(HeaderConstants.REFERRER_POLICY, "no-referrer")
            setHeader(HeaderConstants.STRICT_TRANSPORT_SECURITY, "max-age=31536000 ; includeSubDomains")
            setHeader(HeaderConstants.X_CONTENT_TYPE_OPTIONS, "nosniff")
            setHeader(HeaderConstants.X_FRAME_OPTIONS, "DENY")
            setHeader(HeaderConstants.X_PERMITTED_CROSS_DOMAIN_POLICIES, "none")
        }

        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            filterChain.doFilter(request, response)
        }
    }
}
