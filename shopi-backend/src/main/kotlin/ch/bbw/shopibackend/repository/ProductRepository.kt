package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.Product
import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.generated.jooq.public_.tables.Product.PRODUCT
import ch.bbw.shopibackend.generated.jooq.public_.tables.Specification.SPECIFICATION
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    @Autowired var context: DSLContext
) {

    fun getAll(): MutableList<SimpleProduct> {
        return context.select(
            PRODUCT.ID.`as`("id"),
            PRODUCT.NAME.`as`("name"),
            PRODUCT.DESCRIPTION.`as`("description"),
            PRODUCT.PRICE.`as`("price"),
            PRODUCT.STOCK.`as`("stock"),
            PRODUCT.IMAGE.`as`("imageLink")
        )
            .from(PRODUCT)
            .fetchInto(SimpleProduct::class.java)
    }

    fun getProductWithSpecification(productId: Int): Product? {
        return context.select(
            PRODUCT.ID.`as`("id"),
            PRODUCT.NAME.`as`("name"),
            PRODUCT.DESCRIPTION.`as`("description"),
            PRODUCT.PRICE.`as`("price"),
            PRODUCT.STOCK.`as`("stock"),
            PRODUCT.IMAGE.`as`("imageLink")
        )
            .from(PRODUCT)
            .join(SPECIFICATION).on(SPECIFICATION.PRODUCT_FK.eq(PRODUCT.ID))
            .where(PRODUCT.ID.eq(productId))
            .fetchSingleInto(Product::class.java)
    }

}


