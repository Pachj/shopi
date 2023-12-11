package ch.bbw.shopibackend

import ch.bbw.shopibackend.generated.jooq.public_.tables.Product.PRODUCT
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    @Autowired var context: DSLContext
) {

    fun getAll(): MutableList<Product> {
        return context.select(
            PRODUCT.ID.`as`("id"),
            PRODUCT.NAME.`as`("name"),
            PRODUCT.DESCRIPTION.`as`("description"),
            PRODUCT.PRICE.`as`("price"),
            PRODUCT.STOCK.`as`("stock"),
            PRODUCT.IMAGE.`as`("imageLink")
        )
            .from(PRODUCT)
            .fetchInto(Product::class.java)
    }

    fun getProductWithSpecification() {
        
    }

}


