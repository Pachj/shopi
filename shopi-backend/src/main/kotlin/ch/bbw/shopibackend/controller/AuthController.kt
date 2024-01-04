package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.AuthenticationResponse
import ch.bbw.shopibackend.AuthenticationRequest
import ch.bbw.shopibackend.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
        authenticationService.authentication(authRequest)

}