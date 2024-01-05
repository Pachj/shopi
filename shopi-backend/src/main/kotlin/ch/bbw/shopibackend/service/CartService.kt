package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val tokenService: TokenService,
    private val userService: UserService,
) {

    fun deleteCart() {
        cartRepository.delete()
    }

    fun addItemToCart(product: SimpleProduct, bearerToken: String) {
        val userId = userService.getUserIdFromToken(bearerToken) ?: throw Exception("User not valid")
        cartRepository.add(product.id, userId)
    }

    fun getCart(userId: Int) {
        cartRepository.getCart(userId)
    }

}
