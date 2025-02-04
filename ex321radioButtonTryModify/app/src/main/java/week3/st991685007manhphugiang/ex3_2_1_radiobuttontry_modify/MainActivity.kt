package week3.st991685007manhphugiang.ex3_2_1_radiobuttontry_modify

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import week3.st991685007manhphugiang.ex3_2_1_radiobuttontry_modify.ui.theme.Ex321radioButtonTryModifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex321radioButtonTryModifyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RadioButtonSample(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RadioButtonSample(modifier: Modifier = Modifier, colors: RadioButtonColors = RadioButtonDefaults.colors()) {

    val radioOptions = listOf(
        "Apple" to Color.Red,
        "Banana" to Color.Cyan,
        "Citrus" to Color.Green,
        "Onion" to Color.Magenta
    )

    // Store only the text (first value of Pair)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0].first ) }

    Column (
        modifier = Modifier
            //Make the column fill the entire screen
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // need context for Toast
        val context = LocalContext.current
        // Title Text
        Text(
            text = "RadioButton Demo",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold, // Bold text
            modifier = Modifier
                .padding(top = 16.dp) // Adds some space from the top
        )

        Spacer(modifier = Modifier.height(32.dp)) // Adds space between the title and radio buttons

        // iterates through each text in the radioOptions list to dynamically create
        // a radio button for each option

        radioOptions.forEach { (text, color) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        //remove the toast display
                        //  Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        }
                    )
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        //remove the toast display
                      //  Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    },
                    colors = colors.copy(selectedColor = color) // Override the selected color
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = color,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        //add new text to display user selection
        Spacer(modifier = Modifier.height(24.dp))

        // Display selected option
        Text(
            text = "Selected: $selectedOption",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Blue
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex321radioButtonTryModifyTheme {
        RadioButtonSample()
    }
}
