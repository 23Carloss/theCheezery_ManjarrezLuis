package gonzalez.carlos.thecheezery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import gonzalez.carlos.navigation.MyApp
import gonzalez.carlos.thecheezery.ui.theme.TheCheezeryTheme
import gonzalez.carlos.viewModel.CheezeryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCheezeryTheme {
                Scaffold { innerPadding ->
                    MyApp(innerPadding, viewModel<CheezeryViewModel>())
                }
            }
        }
    }
}
