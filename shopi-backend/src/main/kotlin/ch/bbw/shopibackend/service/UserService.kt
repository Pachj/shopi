package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.ShopiUser
import ch.bbw.shopibackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(user: ShopiUser): ShopiUser {
        return userRepository.create(user)
    }

    fun findById(id: Int): ShopiUser {
        return userRepository.findById(id)
    }

}
