package ch.bbw.shopibackend.controller

data class UserRequest(
    val email: String,
    val password: String,
)

data class UserResponse(
    val id: Int,
    val email: String,
)