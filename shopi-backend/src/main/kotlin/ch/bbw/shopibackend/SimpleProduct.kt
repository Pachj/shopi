package ch.bbw.shopibackend

data class SimpleProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val stock: Int,
    val imageLink: String,
)

data class Product(
    val simpleProduct: SimpleProduct,
    val specification: List<Pair<String, String>>,
)
