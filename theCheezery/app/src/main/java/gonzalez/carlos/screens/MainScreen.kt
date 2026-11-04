package gonzalez.carlos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gonzalez.carlos.data.domain.Product
import gonzalez.carlos.data.models.Brighter_Pink
import gonzalez.carlos.data.models.DisabledGrey
import gonzalez.carlos.data.models.Less_Purple
import gonzalez.carlos.data.models.Pinky
import gonzalez.carlos.data.models.Purple_grey
import gonzalez.carlos.data.models.Very_purple
import gonzalez.carlos.thecheezery.R
import gonzalez.carlos.viewmodel.AppScreen
import gonzalez.carlos.viewmodel.CheezeryViewModel
import gonzalez.carlos.viewmodel.ProductCategory

@Composable
fun MainScreen(viewModel: CheezeryViewModel, modifier: Modifier = Modifier) {
    val state = viewModel.state
    Surface(modifier = modifier.fillMaxSize(), color = Color.White) {
        when (state.screen) {
            AppScreen.Welcome -> WelcomeScreen(viewModel::showLogin)
            AppScreen.Login -> LoginScreen(
                email = state.email,
                password = state.password,
                error = state.loginError,
                onEmailChange = viewModel::updateEmail,
                onPasswordChange = viewModel::updatePassword,
                onLogin = viewModel::login,
                onBack = viewModel::showWelcome
            )
            AppScreen.Menu -> MenuScreen(viewModel.categories, viewModel::openCategory, viewModel::openCart)
            AppScreen.Products -> state.category?.let { category ->
                ProductsScreen(category, viewModel::addToCart, viewModel::openCart, viewModel::backToMenu)
            }
            AppScreen.Cart -> CartScreen(state.cart, viewModel::removeFromCart, viewModel::backToMenu)
        }
    }
}

@Composable
private fun WelcomeScreen(onStart: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Purple_grey)) {
        Image(
            painter = painterResource(R.drawable.the_cheezery),
            contentDescription = "Logotipo iluminado de The Cheezery",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
            Text("Welcome to The Cheezery", color = Color.White, fontSize = 23.sp)
            Spacer(Modifier.height(8.dp))
            Text("Home of the most wonderful desserts ever seen (and tasted) by the human being.", color = Color.White, fontSize = 14.sp)
            Spacer(Modifier.height(22.dp))
            PinkButton("Get started!", onStart, Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
private fun LoginScreen(
    email: String,
    password: String,
    error: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(R.drawable.grupo1), "The Cheezery", Modifier.fillMaxWidth().height(100.dp), contentScale = ContentScale.Fit)
        Spacer(Modifier.height(48.dp))
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        if (error != null) {
            Spacer(Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error, modifier = Modifier.fillMaxWidth())
        }
        Spacer(Modifier.height(24.dp))
        PinkButton("Entrar", onLogin)
        TextButton(onClick = onBack) { Text("Volver", color = Less_Purple) }
    }
}

@Composable
private fun MenuScreen(
    categories: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit,
    onCartClick: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.End) {
            CartIcon(onCartClick)
        }
        Image(
            painterResource(R.drawable.grupo2),
            contentDescription = "The Cheezery",
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = 14.dp),
            contentScale = ContentScale.Fit
        )
        val rows = categories.chunked(2)
        rows.forEach { rowCategories ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                rowCategories.forEach { category ->
                    Button(
                        onClick = { onCategoryClick(category) },
                        modifier = Modifier.weight(1f).height(84.dp).padding(vertical = 4.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink)
                    ) { Text(category.title, color = Color.White) }
                }
                if (rowCategories.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ProductsScreen(
    category: ProductCategory,
    onAdd: (Product) -> Unit,
    onCartClick: () -> Unit,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("‹ Menú", color = Less_Purple) }
            Spacer(Modifier.weight(1f))
            CartIcon(onCartClick)
        }
        Text(
            category.title,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            color = Pinky,
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {
            items(category.products, key = { it.name }) { product ->
                ProductRow(product, onAdd)
            }
        }
    }
}

@Composable
private fun ProductRow(product: Product, onAdd: (Product) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(product.image), product.name,
            Modifier.size(76.dp).clip(CircleShape), contentScale = ContentScale.Crop
        )
        Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
            Text(product.name, color = Less_Purple, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            product.description?.let { Text(it, color = Color.DarkGray, fontSize = 12.sp, maxLines = 3) }
        }
        Text("$${"%.1f".format(product.price)}", color = Very_purple, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(8.dp))
        IconButton(onClick = { onAdd(product) }, modifier = Modifier.size(44.dp).clip(CircleShape).background(Very_purple)) {
            Image(painterResource(R.drawable.add_cart), "Agregar ${product.name} al carrito", Modifier.size(24.dp))
        }
    }
}

@Composable
private fun CartScreen(items: List<gonzalez.carlos.data.domain.CartItem>, onRemove: (Long) -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 12.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("‹ Menú", color = Less_Purple) }
        }
        Text("My Order", modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), textAlign = TextAlign.Center, color = Pinky, fontFamily = FontFamily.Cursive, fontSize = 40.sp)
        if (items.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Add some products to your order!", color = Purple_grey, textAlign = TextAlign.Center, fontSize = 18.sp)
            }
        } else {
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(items, key = { it.id }) { item ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(item.product.name, modifier = Modifier.weight(1f), color = Color.DarkGray, fontSize = 18.sp)
                        Text("$${"%.1f".format(item.product.price)}", color = Very_purple, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        IconButton(onClick = { onRemove(item.id) }) {
                            Image(painterResource(R.drawable.delete), "Eliminar ${item.product.name}", Modifier.size(24.dp))
                        }
                    }
                }
            }
        }
        val total = items.sumOf { it.product.price.toDouble() }
        Row(Modifier.fillMaxWidth().padding(vertical = 20.dp), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
            Text("Total", fontSize = 32.sp, color = Color.DarkGray)
            Spacer(Modifier.width(12.dp))
            Text("$${"%.1f".format(total)}", fontSize = 38.sp, color = Less_Purple, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun CartIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(54.dp).clip(CircleShape).background(Brighter_Pink)) {
        Image(painterResource(R.drawable.cart), "Abrir carrito", Modifier.size(28.dp))
    }
}

@Composable
private fun PinkButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier, shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink, disabledContainerColor = DisabledGrey)) {
        Text(text, color = Color.White)
    }
}
