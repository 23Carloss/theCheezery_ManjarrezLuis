package gonzalez.carlos.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import gonzalez.carlos.data.domain.CartItem
import gonzalez.carlos.data.domain.Product
import gonzalez.carlos.data.models.coldDrinks
import gonzalez.carlos.data.models.hotDrinks
import gonzalez.carlos.data.models.salties
import gonzalez.carlos.data.models.sweets
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class AppScreen { Welcome, Login, Menu, Products, Cart }

data class ProductCategory(val title: String, val products: List<Product>)

data class CheezeryUiState(
    val screen: AppScreen = AppScreen.Welcome,
    val category: ProductCategory? = null,
    val email: String = "",
    val password: String = "",
    val loginError: String? = null,
    val cart: List<CartItem> = emptyList()
)

class CheezeryViewModel : ViewModel() {
    var state by mutableStateOf(CheezeryUiState())
        private set

    val categories = listOf(
        ProductCategory("Hot Drinks", hotDrinks),
        ProductCategory("Cold Drinks", coldDrinks),
        ProductCategory("Salties", salties),
        ProductCategory("Sweets", sweets)
    )

    private var nextCartId = 0L

    fun showLogin() { state = state.copy(screen = AppScreen.Login, loginError = null) }
    fun showWelcome() { state = state.copy(screen = AppScreen.Welcome) }
    fun updateEmail(value: String) { state = state.copy(email = value, loginError = null) }
    fun updatePassword(value: String) { state = state.copy(password = value, loginError = null) }

    fun login() {
        val email = state.email.trim()
        state = when {
            email.isBlank() || state.password.isBlank() -> state.copy(loginError = "Completa el correo y la contraseña.")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> state.copy(loginError = "Escribe un correo electrónico válido.")
            else -> state.copy(screen = AppScreen.Menu, email = email, loginError = null)
        }
    }

    fun openCategory(category: ProductCategory) {
        state = state.copy(screen = AppScreen.Products, category = category)
    }

    fun openCart() { state = state.copy(screen = AppScreen.Cart) }
    fun backToMenu() { state = state.copy(screen = AppScreen.Menu) }

    fun addToCart(product: Product) {
        state = state.copy(cart = state.cart + CartItem(nextCartId++, product))
    }

    fun removeFromCart(itemId: Long) {
        state = state.copy(cart = state.cart.filterNot { it.id == itemId })
    }
}
