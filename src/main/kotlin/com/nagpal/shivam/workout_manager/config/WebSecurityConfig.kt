package com.nagpal.shivam.workout_manager.config

import com.nagpal.shivam.workout_manager.filters.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val jwtFilter: JwtFilter,
    private val jwtAuthenticationHandler: JwtAuthenticationHandler
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers("/actuator", "/actuator/*").permitAll().anyRequest()
            .authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationHandler)
    }
}
