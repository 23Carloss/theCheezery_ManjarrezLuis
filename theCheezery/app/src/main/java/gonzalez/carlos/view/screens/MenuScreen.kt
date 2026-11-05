package gonzalez.carlos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gonzalez.carlos.components.CartButton
import gonzalez.carlos.components.CategoryButton
import gonzalez.carlos.data.domain.ProductCategory
import gonzalez.carlos.data.models.productCategories
import gonzalez.carlos.thecheezery.R

@Composable
fun MenuScreen(
    onCategoryClick: (ProductCategory) -> Unit,
    onCartClick: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.End) {
            CartButton(onCartClick)
        }
        Image(
            painter = painterResource(R.drawable.grupo2),
            contentDescription = "The Cheezery",
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = 14.dp),
            contentScale = ContentScale.Fit
        )
        productCategories.chunked(2).forEach { rowCategories ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                rowCategories.forEach { category ->
                    Column(Modifier.weight(1f)) {
                        CategoryButton(category, onCategoryClick)
                    }
                }
                if (rowCategories.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}
