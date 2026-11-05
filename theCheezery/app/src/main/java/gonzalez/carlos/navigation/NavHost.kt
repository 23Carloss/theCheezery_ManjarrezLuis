package gonzalez.carlos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import gonzalez.carlos.data.models.getCategoryById
import gonzalez.carlos.view.screens.CartScreen
import gonzalez.carlos.view.screens.LoginScreen
import gonzalez.carlos.view.screens.MenuScreen
import gonzalez.carlos.view.screens.ProductsScreen
import gonzalez.carlos.view.screens.WelcomeScreen
import gonzalez.carlos.viewModel.CheezeryViewModel

@Composable
fun MyApp(
    innerPadding: PaddingValues,
    viewModel: CheezeryViewModel = viewModel()
) {
    val navController = rememberNavController()
    val state = viewModel.state

    NavHost(
        navController = navController,
        startDestination = Welcome,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Welcome> {
            WelcomeScreen(onStart = { navController.navigate(Login) })
        }

        composable<Login> {
            LoginScreen(
                email = state.email,
                password = state.password,
                error = state.loginError,
                onEmailChange = viewModel::updateEmail,
                onPasswordChange = viewModel::updatePassword,
                onLogin = {
                    if (viewModel.validateLogin()) {
                        navController.navigate(Menu) {
                            popUpTo<Welcome> { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable<Menu> {
            MenuScreen(
                onCategoryClick = { category -> navController.navigate(Products(category.id)) },
                onCartClick = { navController.navigate(Cart) }
            )
        }

        composable<Products> { entry ->
            val categoryId = entry.toRoute<Products>().categoryId
            val category = getCategoryById(categoryId)
            if (category != null) {
                ProductsScreen(
                    category = category,
                    onAddToCart = viewModel::addToCart,
                    onCartClick = { navController.navigate(Cart) },
                    onBack = { navController.popBackStack() }
                )
            } else {
                MenuScreen(
                    onCategoryClick = { selected -> navController.navigate(Products(selected.id)) },
                    onCartClick = { navController.navigate(Cart) }
                )
            }
        }

        composable<Cart> {
            CartScreen(
                items = state.cart,
                onRemove = viewModel::removeFromCart,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
