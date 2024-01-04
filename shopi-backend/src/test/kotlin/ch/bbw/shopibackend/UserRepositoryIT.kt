package ch.bbw.shopibackend

import ch.bbw.shopibackend.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserRepositoryIT {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun shouldCreateUser() {
        val newUser = ShopiUser(
            email = "test@mail.com",
            password = "mytestpassword",
            role = Role.USER
        )

        val createdUser = userRepository.create(newUser)

        assertThat(createdUser).isNotNull
        assertThat(createdUser.id).isNotNull
    }

}