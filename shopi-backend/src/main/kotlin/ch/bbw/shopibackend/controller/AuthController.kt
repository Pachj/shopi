package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.AuthenticationResponse
import ch.bbw.shopibackend.AuthenticationRequest
import ch.bbw.shopibackend.service.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = ["http://localhost:3000"])
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
        authenticationService.authentication(authRequest)

}
