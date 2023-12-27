package ch.bbw.shopibackend.service

import ch.bbw.shopibackend.Product
import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun fetchSimpleProducts(): MutableList<SimpleProduct> {
        return productRepository.getAll()
    }

    fun fetchProduct(productId: Int) : Product? {
        return productRepository.getProductWithSpecification(productId)
    }

}