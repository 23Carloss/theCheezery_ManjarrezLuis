package gonzalez.carlos.data.domain

data class Product(val name: String = "",
                   val price: Float = 0F,
                   val image: Int,
                   val description: String? = null
)