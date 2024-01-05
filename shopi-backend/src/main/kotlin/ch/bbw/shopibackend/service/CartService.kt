package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.model.Cart
import ch.bbw.shopibackend.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val userService: UserService,
) {

    fun deleteCart() {
        cartRepository.delete()
    }

    fun addItemToCart(product: SimpleProduct, bearerToken: String) {
        val userId = userService.getUserIdFromToken(bearerToken) ?: throw Exception("User not valid")
        cartRepository.add(product.id, userId)
    }

    fun getCart(bearerToken: String): Cart? {
        val userId = userService.getUserIdFromToken(bearerToken) ?: throw Exception("User not valid")
        return cartRepository.getCart(userId)
    }

}
