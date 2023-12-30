package ch.bbw.shopibackend.repository

import ch.bbw.shopibackend.Product
import ch.bbw.shopibackend.SimpleProduct
import ch.bbw.shopibackend.generated.jooq.public_.tables.Product.PRODUCT
import ch.bbw.shopibackend.generated.jooq.public_.tables.Specification.SPECIFICATION
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val context: DSLContext
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

    fun getProductWithSpecification(productId: Int): Product {
        val record = context.select(
            PRODUCT.ID,
            PRODUCT.NAME,
            PRODUCT.DESCRIPTION,
            PRODUCT.PRICE,
            PRODUCT.STOCK,
            PRODUCT.IMAGE,
            DSL.groupConcat(SPECIFICATION.NAME).separator(", ").`as`("spec_name"),
            DSL.groupConcat(SPECIFICATION.VALUE).separator(", ").`as`("spec_value")
        )
            .from(PRODUCT)
            .leftJoin(SPECIFICATION).on(SPECIFICATION.PRODUCT_FK.eq(PRODUCT.ID))
            .where(PRODUCT.ID.eq(productId))
            .groupBy(PRODUCT.ID)
            .fetchSingle()
        return record.mapTo()
    }

    private fun Record.mapTo(): Product {
        val specifications = mutableListOf<Pair<String, String>>()
        val names = (this.get("spec_name") as String).split(",")
        val values = (this.get("spec_value") as String).split(",")

        for (i in names.indices) {
            val pair = names[i] to values[i]
            specifications.add(pair)
        }

        return Product(
            SimpleProduct(
                id = this.get(PRODUCT.ID),
                name = this.get(PRODUCT.NAME),
                description = this.get(PRODUCT.DESCRIPTION),
                price = this.get(PRODUCT.PRICE),
                stock = this.get(PRODUCT.STOCK),
                imageLink = this.get(PRODUCT.IMAGE),
            ),
            specification = specifications
        )
    }

}


