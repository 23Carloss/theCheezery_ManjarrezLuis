package gonzalez.carlos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gonzalez.carlos.data.domain.Product
import gonzalez.carlos.data.domain.ProductCategory
import gonzalez.carlos.data.models.Brighter_Pink
import gonzalez.carlos.data.models.Less_Purple
import gonzalez.carlos.data.models.Very_purple
import gonzalez.carlos.thecheezery.R

@Composable
fun CategoryButton(category: ProductCategory, onClick: (ProductCategory) -> Unit) {
    Button(
        onClick = { onClick(category) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink)
    ) {
        Text(category.title, color = Color.White, modifier = Modifier.padding(vertical = 10.dp))
    }
}

@Composable
fun CheezeryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink)
    ) {
        Text(text, color = Color.White)
    }
}

@Composable
fun CartButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(54.dp).clip(CircleShape).background(Brighter_Pink)
    ) {
        Image(painterResource(R.drawable.cart), "Abrir carrito", Modifier.size(28.dp))
    }
}

@Composable
fun ProductRow(product: Product, onAddToCart: (Product) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(product.image),
            contentDescription = product.name,
            modifier = Modifier.size(76.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(1f).padding(horizontal = 10.dp)) {
            Text(product.name, color = Less_Purple, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            product.description?.let { Text(it, color = Color.DarkGray, fontSize = 12.sp, maxLines = 3) }
        }
        Text("$${product.price.asPrice()}", color = Very_purple, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(8.dp))
        IconButton(
            onClick = { onAddToCart(product) },
            modifier = Modifier.size(44.dp).clip(CircleShape).background(Very_purple)
        ) {
            Image(painterResource(R.drawable.add_cart), "Agregar ${product.name} al carrito", Modifier.size(24.dp))
        }
    }
}

fun Float.asPrice(): String = "%.1f".format(this)
