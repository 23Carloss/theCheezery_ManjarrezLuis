package gonzalez.carlos.data.domain

data class ProductCategory(
    val id: String,
    val title: String,
    val products: List<Product>
)
