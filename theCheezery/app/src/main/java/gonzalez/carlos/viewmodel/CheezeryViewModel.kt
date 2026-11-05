package gonzalez.carlos.viewModel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import gonzalez.carlos.data.domain.CartItem
import gonzalez.carlos.data.domain.Product

data class CheezeryUiState(
    val email: String = "",
    val password: String = "",
    val loginError: String? = null,
    val cart: List<CartItem> = emptyList()
)

class CheezeryViewModel : ViewModel() {
    var state by mutableStateOf(CheezeryUiState())
        private set

    private var nextCartId = 0L

    fun updateEmail(value: String) {
        state = state.copy(email = value, loginError = null)
    }

    fun updatePassword(value: String) {
        state = state.copy(password = value, loginError = null)
    }

    /** Returns true when the required fields and email format are valid. */
    fun validateLogin(): Boolean {
        val email = state.email.trim()
        val error = when {
            email.isBlank() || state.password.isBlank() -> "Completa el correo y la contraseña."
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Escribe un correo electrónico válido."
            else -> null
        }
        state = state.copy(email = email, loginError = error)
        return error == null
    }

    fun addToCart(product: Product) {
        state = state.copy(cart = state.cart + CartItem(nextCartId++, product))
    }

    fun removeFromCart(itemId: Long) {
        state = state.copy(cart = state.cart.filterNot { it.id == itemId })
    }
}
