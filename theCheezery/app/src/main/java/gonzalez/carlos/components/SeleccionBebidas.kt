package gonzalez.carlos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import gonzalez.carlos.data.models.Very_purple

@Composable
fun ColumnaTipoBebida(
                tipo1 :String,
                tipo2 :String,
                tipo3 :String){
    Column(modifier = Modifier.fillMaxWidth().background(color = Very_purple),
            horizontalAlignment = Alignment.CenterHorizontally){
        Row(){
            Text(text = tipo1, color = Color.White)

        }
        Row(modifier = Modifier){
            Text(text = tipo2, color = Color.White)

        }
        Row(modifier = Modifier){
            Text(text = tipo3, color = Color.White)

        }
    }

}
@Preview
@Composable
fun PreviewColumna(){
    ColumnaTipoBebida("Hot Drinks", "Salties", "Combos")
}

