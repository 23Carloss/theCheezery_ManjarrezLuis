package gonzalez.carlos.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gonzalez.carlos.components.CartButton
import gonzalez.carlos.components.ProductRow
import gonzalez.carlos.data.domain.Product
import gonzalez.carlos.data.domain.ProductCategory
import gonzalez.carlos.data.models.Pinky

@Composable
fun ProductsScreen(
    category: ProductCategory,
    onAddToCart: (Product) -> Unit,
    onCartClick: () -> Unit,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("‹ Menú") }
            Spacer(Modifier.weight(1f))
            CartButton(onCartClick)
        }
        Text(
            text = category.title,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            color = Pinky,
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp
        )
        LazyColumn(Modifier.weight(1f)) {
            items(category.products, key = { it.name }) { product ->
                ProductRow(product, onAddToCart)
            }
        }
    }
}
