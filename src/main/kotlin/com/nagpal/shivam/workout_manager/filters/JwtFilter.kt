package com.nagpal.shivam.workout_manager.filters

import com.nagpal.shivam.workout_manager.dtos.jwt.TokenPayload
import com.nagpal.shivam.workout_manager.helpers.impl.JwtHelper
import com.nagpal.shivam.workout_manager.utils.Constants
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*


@Component
class JwtFilter @Autowired constructor(private val jwtHelper: JwtHelper) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            val authorizationHeader = request.getHeader(Constants.AUTHORIZATION)

            if (authorizationHeader != null) {
                val tokenPayload: TokenPayload = jwtHelper.verifyAndDecode(authorizationHeader)
                val user = User(
                    tokenPayload.userId.toString(), UUID.randomUUID().toString(),
                    tokenPayload.roles.map { role: String? -> SimpleGrantedAuthority(role) }
                )
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(user, null, user.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        } catch (_: Exception) {
        }
        filterChain.doFilter(request, response)
    }
}
