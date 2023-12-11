package ch.bbw.shopibackend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired val productRepository: ProductRepository
) {

    fun fetchProducts(): MutableList<Product> {
        return productRepository.getAll()
    }

}