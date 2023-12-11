package ch.bbw.shopibackend

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val stock: Int,
    val imageLink: String,
)
