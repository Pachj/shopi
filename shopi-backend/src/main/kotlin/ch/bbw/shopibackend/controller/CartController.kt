package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService,
) {

    @GetMapping
    fun getCart() {

    }

    @PostMapping
    fun addProduct(@RequestHeader("Authorization") bearerToken: String, @RequestBody product: SimpleProduct) {
        cartService.addItemToCart(product, bearerToken)
    }

    @DeleteMapping
    fun deleteProduct() {

    }
}
