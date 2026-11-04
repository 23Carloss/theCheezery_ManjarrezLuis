package gonzalez.carlos.thecheezery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import gonzalez.carlos.screens.MainScreen
import gonzalez.carlos.thecheezery.ui.theme.TheCheezeryTheme
import gonzalez.carlos.viewmodel.CheezeryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCheezeryTheme {
                Scaffold { innerPadding ->
                    MainScreen(
                        viewModel = viewModel<CheezeryViewModel>(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
