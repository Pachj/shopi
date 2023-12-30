package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.service.CartService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController(
    private val cartService: CartService,
) {

    @GetMapping("/cart")
    fun getCart() {

    }

    @PostMapping("/cart")
    fun addProduct() {

    }

    @DeleteMapping("/cart")
    fun deleteCart() {

    }

    @DeleteMapping("/cart")
    fun deleteProduct() {

    }
}
