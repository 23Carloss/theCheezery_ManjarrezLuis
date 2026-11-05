package gonzalez.carlos.data.models

import gonzalez.carlos.data.domain.ProductCategory

val productCategories = listOf(
    ProductCategory("hot_drinks", "Hot Drinks", hotDrinks),
    ProductCategory("cold_drinks", "Cold Drinks", coldDrinks),
    ProductCategory("salties", "Salties", salties),
    ProductCategory("sweets", "Sweets", sweets)
)

fun getCategoryById(id: String): ProductCategory? = productCategories.firstOrNull { it.id == id }
