package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.ShopiUser
import ch.bbw.shopibackend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping(
        "/user",
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createUser(@RequestBody user: ShopiUser): ResponseEntity<ShopiUser> {
        val createdUser = userService.createUser(user)
        return if (createdUser.id.toString().isEmpty()) {
            ResponseEntity(createdUser, HttpStatus.CREATED)
        } else {
            ResponseEntity(createdUser, HttpStatus.CREATED)
        }

    }
}