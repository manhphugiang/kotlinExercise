package week3.st991685007manhphugiang.ex3_1_checkbox

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import week3.st991685007manhphugiang.ex3_1_checkbox.ui.theme.Ex31checkboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex31checkboxTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Greeting()
                    chooseHobbies()
                }
            }
        }
    }
}

@Composable
fun chooseHobbies(modifier: Modifier = Modifier) {
    var painting by remember { mutableStateOf(false) }
    var reading by remember { mutableStateOf(false) }
    var singing by remember { mutableStateOf(false) }
    var cooking by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Choose your hobbies: ")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                "Painting"
            )
            Checkbox(
                checked = painting,
                onCheckedChange = { painting = it }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                "Reading"
            )
            Checkbox(
                checked = reading,
                onCheckedChange = { reading = it }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                "Singing"
            )
            Checkbox(
                checked = singing,
                onCheckedChange = { singing = it }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                "Cooking"
            )
            Checkbox(
                checked = cooking,
                onCheckedChange = { cooking = it }
            )
        }


        SubmitButton {
            val selectedHobbies = listOfNotNull(
                if (painting) "Painting" else null,
                if (reading) "Reading" else null,
                if (singing) "Singing" else null,
                if (cooking) "Cooking" else null
            ).joinToString(", ")

            val message = if (selectedHobbies.isNotEmpty()) {
                "$selectedHobbies selected"
            } else {
                "No hobbies selected"
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}


@Composable
fun SubmitButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(0.5f)

    ) {
        Text(text = "Submit")
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Manh Phu Giang - stID: 991685007",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(top = 30.dp, bottom = 16.dp).background(color = Color.Cyan)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex31checkboxTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting()
            chooseHobbies()
        }    }
}