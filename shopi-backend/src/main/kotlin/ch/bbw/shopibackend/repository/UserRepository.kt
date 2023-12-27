package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.ShopiUser
import org.jooq.DSLContext
import org.jooq.generated.public_.tables.Shopiuser.SHOPIUSER
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    var context: DSLContext
) {

    fun create(user: ShopiUser): ShopiUser {
        val responseValue = context.insertInto(SHOPIUSER, SHOPIUSER.EMAIL, SHOPIUSER.PASSWORD)
            .values(user.email, user.password)
            .execute()

        return if (responseValue == 0) {
            user
        } else {
            getUser(user.email)
        }
    }

    fun getUser(email: String): ShopiUser {
        return context.select(
            SHOPIUSER.ID.`as`("id"),
            SHOPIUSER.EMAIL.`as`("email"),
            SHOPIUSER.ID.`as`("password"),
        )
            .from(SHOPIUSER)
            .where(SHOPIUSER.EMAIL.eq(email))
            .fetchSingleInto(ShopiUser::class.java)
    }
}