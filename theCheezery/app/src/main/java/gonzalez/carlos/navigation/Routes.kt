package gonzalez.carlos.navigation

import kotlinx.serialization.Serializable

@Serializable
object Welcome

@Serializable
object Login

@Serializable
object Menu

@Serializable
data class Products(val categoryId: String)

@Serializable
object Cart
