package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.model.Cart
import ch.bbw.shopibackend.model.CartItem
import org.jooq.DSLContext
import org.jooq.generated.public_.Tables.*
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

    fun getCart(userId: Int): Cart? {
        val cartItems = context.select(
            PRODUCT.ID.`as`("id"),
            PRODUCT.NAME.`as`("name"),
            PRODUCT.DESCRIPTION.`as`("description"),
            PRODUCT.PRICE.`as`("price"),
            PRODUCT.STOCK.`as`("stock"),
            PRODUCT.IMAGE.`as`("imageLink"),
            CART_PRODUCT.COUNT.`as`("amount"),
        )
            .from(CART)
            .leftJoin(CART_PRODUCT).on(CART_PRODUCT.CART_FK.eq(CART.ID))
            .leftJoin(PRODUCT).on(PRODUCT.ID.eq(CART_PRODUCT.PRODUCT_FK))
            .where(CART.USER_FK.eq(userId))
            .fetch()

        if (cartItems.isEmpty()) {
            return null
        }

        return Cart(cartItems.map {
            CartItem(
                product = SimpleProduct(
                    id = it.get("id", Int::class.java),
                    name = it.get("name", String::class.java),
                    description = it.get("description", String::class.java),
                    price = it.get("price", String::class.java),
                    stock = it.get("stock", Int::class.java),
                    imageLink = it.get("imageLink", String::class.java),
                ),
                amount = it.get("amount", Int::class.java),
            )
        })
    }
}
