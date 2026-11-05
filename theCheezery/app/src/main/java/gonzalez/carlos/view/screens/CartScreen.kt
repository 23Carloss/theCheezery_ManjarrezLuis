package gonzalez.carlos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gonzalez.carlos.components.asPrice
import gonzalez.carlos.data.domain.CartItem
import gonzalez.carlos.data.models.Less_Purple
import gonzalez.carlos.data.models.Pinky
import gonzalez.carlos.data.models.Very_purple
import gonzalez.carlos.thecheezery.R

@Composable
fun CartScreen(items: List<CartItem>, onRemove: (Long) -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 12.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("‹ Menú", color = Less_Purple) }
        }
        Text(
            "My Order",
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = Pinky,
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp
        )
        if (items.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Add some products to your order!", color = Very_purple, textAlign = TextAlign.Center, fontSize = 18.sp)
            }
        } else {
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items, key = { it.id }) { item ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(item.product.name, modifier = Modifier.weight(1f), color = androidx.compose.ui.graphics.Color.DarkGray, fontSize = 18.sp)
                        Text("$${item.product.price.asPrice()}", color = Very_purple, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        IconButton(onClick = { onRemove(item.id) }) {
                            Image(painterResource(R.drawable.delete), "Eliminar ${item.product.name}", Modifier.size(24.dp))
                        }
                    }
                }
            }
        }
        val total = items.sumOf { it.product.price.toDouble() }
        Row(
            Modifier.fillMaxWidth().padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total", fontSize = 32.sp, color = androidx.compose.ui.graphics.Color.DarkGray)
            Spacer(Modifier.size(12.dp))
            Text("$${"%.1f".format(total)}", fontSize = 38.sp, color = Less_Purple, fontWeight = FontWeight.Bold)
        }
    }
}
