package ch.bbw.shopibackend

import ch.bbw.shopibackend.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductRepositoryIT {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun shouldReturnAllSimpleProduct() {
        val simpleProductList = productRepository.getAll()
        assertThat(simpleProductList.size).isEqualTo(10)
    }

    @Test
    fun shouldFetchCompleteProduct() {
        val product = productRepository.getProductWithSpecification(1)
        assertThat(product).isNotNull
        assertThat(product.simpleProduct).isNotNull
        assertThat(product.specification.size).isEqualTo(5)
    }



}