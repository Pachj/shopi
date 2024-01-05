package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.ShopiUser
import ch.bbw.shopibackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) {

    fun createUser(user: ShopiUser): ShopiUser {
        return userRepository.create(user)
    }

    fun findById(id: Int): ShopiUser {
        return userRepository.findById(id)
    }

    fun findByEmail(email: String): ShopiUser {
        return userRepository.findByEmail(email)
    }

    fun getUserIdFromToken(bearerToken: String): Int? {
        val userEmail = tokenService.extractEmail(bearerToken.substringAfter("Bearer "))
        if (userEmail != null) {
            return findByEmail(userEmail).id
        }
        return null
    }
}
