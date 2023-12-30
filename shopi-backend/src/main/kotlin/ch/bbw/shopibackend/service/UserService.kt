package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.ShopiUser
import ch.bbw.shopibackend.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(user: ShopiUser): ShopiUser {
        user.password!!.salt()
        return userRepository.create(user)
    }

    private fun String.salt(): String {
        val encoder = BCryptPasswordEncoder(16)
        return encoder.encode(this)
    }
}