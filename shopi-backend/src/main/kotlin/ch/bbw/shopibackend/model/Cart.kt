package ch.bbw.shopibackend.model

import ch.bbw.shopibackend.SimpleProduct

data class Cart(
    val items: List<CartItem?>
)

data class CartItem(
    val product: SimpleProduct,
    val amount: Int,
)

