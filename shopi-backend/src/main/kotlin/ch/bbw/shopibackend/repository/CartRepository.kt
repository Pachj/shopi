package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.Product
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CartRepository(
    private val context: DSLContext
) {

    fun delete() {
    }

    fun add() {

    }

    fun getCart(userId: Int) : List<Product>{
    }
}