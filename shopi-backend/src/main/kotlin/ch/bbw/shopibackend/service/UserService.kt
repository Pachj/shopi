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
        hash(user.password)
        return userRepository.create(user)
    }

    private fun hash(password: String): String {
        val encoder = BCryptPasswordEncoder(16)
        return encoder.encode(password)
    }
}