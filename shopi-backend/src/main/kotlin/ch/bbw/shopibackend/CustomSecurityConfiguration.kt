package ch.bbw.shopibackend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class CustomSecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtTokenFilter: JwtTokenFilter): DefaultSecurityFilterChain =
        http
            .cors { cors ->
                cors.configurationSource { request ->
                    val configuration = CorsConfiguration()
                    configuration.allowedOrigins = listOf("*")
                    configuration.allowedMethods = listOf("*")
                    configuration.allowedHeaders = listOf("*")
                    configuration
                }
            }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/auth",
                        "/auth/refresh",
                        "/error",
                        "/products",
                        "/login",
                        "/user"
                    ).permitAll()
                    .anyRequest().fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

            .build()

}
