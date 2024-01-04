package ch.bbw.shopibackend

data class ShopiUser(
    var id: Int? = null,
    val email: String,
    var password: String? = null,
    val role: Role,
)

enum class Role {
    USER, ADMIN
}
