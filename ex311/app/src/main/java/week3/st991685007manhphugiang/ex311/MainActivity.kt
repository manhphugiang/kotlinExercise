package week3.st991685007manhphugiang.ex311

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import week3.st991685007manhphugiang.ex311.ui.theme.Ex311Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex311Theme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    checkBoxMinimalExample()

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Manh Phu Giang - stID: 991685007",
        modifier = modifier
    )
}

@Composable
fun checkBoxMinimalExample(){
    var checked by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    ){
        Text(
            "Minimal Checkbox"
        )
        Checkbox(
            checked = checked,
            onCheckedChange = {checked = it}
        )
    }

    Text(
        if(checked) "Checkbox is checked" else "checkbox is unchecked",
        modifier = Modifier.padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex311Theme {
        checkBoxMinimalExample()
    }
}
