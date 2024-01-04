package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.Role
import ch.bbw.shopibackend.ShopiUser
import ch.bbw.shopibackend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): UserResponse {
        val createUser = userService.createUser(
            user = userRequest.toModel()
        )
        return createUser.toResponse()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): UserResponse {
        val foundUser = userService.findById(id)
        return foundUser.toResponse()
    }

    private fun UserRequest.toModel(): ShopiUser =
        ShopiUser(
            email = this.email,
            password = this.password,
            role = Role.USER,
        )

    private fun ShopiUser.toResponse(): UserResponse =
        UserResponse(
            id = this.id!!,
            email = this.email,
        )
}