package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.model.Cart
import ch.bbw.shopibackend.model.CartItem
import ch.bbw.shopibackend.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService,
) {

    @GetMapping
    fun getCart(@RequestHeader("Authorization") bearerToken: String): Cart? {
        return cartService.getCart(bearerToken)
    }

    @PostMapping
    fun addProduct(@RequestHeader("Authorization") bearerToken: String, @RequestBody product: SimpleProduct) {
        cartService.addItemToCart(product, bearerToken)
    }

    @PutMapping
    fun changeAmount(@RequestHeader("Authorization") bearerToken: String, @RequestBody cartItem: CartItem): Cart? {
        return cartService.changeAmount(cartItem.product, bearerToken, cartItem.amount)
    }
}
