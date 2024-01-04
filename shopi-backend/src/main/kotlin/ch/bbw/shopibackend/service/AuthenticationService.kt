package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.AuthenticationResponse
import ch.bbw.shopibackend.AuthenticationRequest
import ch.bbw.shopibackend.JwtProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generateToken(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.expiration)
        )

        return AuthenticationResponse(accessToken = accessToken)
    }
}