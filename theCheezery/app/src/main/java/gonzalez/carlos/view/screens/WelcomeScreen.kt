package gonzalez.carlos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gonzalez.carlos.components.CheezeryButton
import gonzalez.carlos.data.models.Purple_grey
import gonzalez.carlos.thecheezery.R

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
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
            Text(
                "Home of the most wonderful desserts ever seen (and tasted) by the human being.",
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(22.dp))
            CheezeryButton("Get started!", onStart, Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
