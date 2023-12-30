package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
) {

    fun deleteCart() {
        cartRepository.delete()
    }

    fun addItemToCart(product: SimpleProduct) {
        cartRepository.add()
    }

    fun getCart(userId: Int){
        cartRepository.getCart(userId)
    }

}