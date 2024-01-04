package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.ShopiUser
import org.jooq.DSLContext
import org.jooq.generated.public_.tables.Shopiuser.SHOPIUSER
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val context: DSLContext,
    private val encoder: PasswordEncoder
) {

    fun create(user: ShopiUser): ShopiUser {
        val responseValue = context.insertInto(SHOPIUSER, SHOPIUSER.EMAIL, SHOPIUSER.PASSWORD)
            .values(user.email, encoder.encode(user.password))
            .execute()

        return if (responseValue == 0) {
            user
        } else {
            findByEmail(user.email)
        }
    }

    fun findByEmail(email: String): ShopiUser {
        return context.select(
            SHOPIUSER.ID.`as`("id"),
            SHOPIUSER.EMAIL.`as`("email"),
            SHOPIUSER.PASSWORD.`as`("password")
        )
            .from(SHOPIUSER)
            .where(SHOPIUSER.EMAIL.eq(email))
            .fetchSingleInto(ShopiUser::class.java)
    }

    fun findById(id: Int): ShopiUser {
        return context.select(
            SHOPIUSER.ID.`as`("id"),
            SHOPIUSER.EMAIL.`as`("email"),
            SHOPIUSER.PASSWORD.`as`("password")
        )
            .from(SHOPIUSER)
            .where(SHOPIUSER.ID.eq(id))
            .fetchSingleInto(ShopiUser::class.java)
    }
}