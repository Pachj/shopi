package ch.bbw.shopibackend.repository

import org.jooq.DSLContext
import org.jooq.generated.public_.Tables.CART
import org.jooq.generated.public_.Tables.CART_PRODUCT
import org.springframework.stereotype.Repository

@Repository
class CartRepository(
    private val context: DSLContext
) {

    fun delete() {
    }

    fun add(productId: Int, userId: Int) {
        // check if user has cart for productId
        var cartId = context.select(CART.ID)
            .from(CART)
            .where(CART.USER_FK.eq(userId))
            .fetchOneInto(Int::class.java)

        if (cartId != null) {
            // increase amount on cart_product by 1
            context.update(CART_PRODUCT)
                .set(CART_PRODUCT.COUNT, CART_PRODUCT.COUNT.plus(1))
                .where(CART_PRODUCT.CART_FK.eq(cartId))
                .and(CART_PRODUCT.PRODUCT_FK.eq(productId))
                .execute()

        } else {
            cartId = context.insertInto(CART, CART.USER_FK)
                .values(userId)
                .returningResult(CART.ID)
                .fetchOneInto(Int::class.java)

            context.insertInto(CART_PRODUCT, CART_PRODUCT.COUNT, CART_PRODUCT.CART_FK, CART_PRODUCT.PRODUCT_FK)
                .values(1, cartId, productId)
                .execute()
        }
    }

    fun getCart(userId: Int) {
    }
}
