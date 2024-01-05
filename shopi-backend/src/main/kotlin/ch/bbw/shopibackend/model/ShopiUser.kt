package ch.bbw.shopibackend

data class ShopiUser(
    var id: Int? = null,
    val email: String,
    var password: String? = null,
)
