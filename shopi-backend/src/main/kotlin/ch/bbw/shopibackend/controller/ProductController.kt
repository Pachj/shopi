package ch.bbw.shopibackend.controller

import ch.bbw.shopibackend.Product
import ch.bbw.shopibackend.service.ProductService
import ch.bbw.shopibackend.SimpleProduct
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("/products")
    fun productOverview(): List<SimpleProduct> {
        return productService.fetchSimpleProducts()
    }

    @GetMapping("/products/{id}")
    fun productDetails(@PathVariable id: Int): Product? {
        return productService.fetchProduct(id)
    }

}