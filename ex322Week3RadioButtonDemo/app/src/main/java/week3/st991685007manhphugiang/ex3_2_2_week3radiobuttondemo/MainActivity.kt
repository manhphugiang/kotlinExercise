package week3.st991685007manhphugiang.ex3_2_2_week3radiobuttondemo

import android.os.Bundle
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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import week3.st991685007manhphugiang.ex3_2_2_week3radiobuttondemo.ui.theme.Ex322Week3RadioButtonDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex322Week3RadioButtonDemoTheme {
                    RadioButtonTShirtSize()
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun RadioButtonTShirtSize(modifier: Modifier = Modifier) {
    val radioOptions = listOf("Small", "Medium", "Large", "Extra Large", " Extra Extra Large")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    var showDialog by remember { mutableStateOf(false) } // dialog visibility


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

        Text(
            text = "What's is your T-shirt size?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold, // Bold text
            modifier = Modifier
                .padding(top = 16.dp) // Adds some space from the top
        )

        Spacer(modifier = Modifier.height(32.dp)) // Adds space between the title and radio buttons

        // iterates through each text in the radioOptions list to dynamically create
        // a radio button for each option

        radioOptions.forEach { text ->
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
                    }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        //add new text to display user selection
        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = { showDialog = true }
        ) {
            Text("Submit")
        }

        if (showDialog) {
            AlertDialogExample(
                dialogTitle = "Your Shirt Size",
                dialogText = "Selected: $selectedOption",
                onConfirmation = {
                    println("Confirmed!")
                    showDialog = false
                },
                onDismissRequest = { showDialog = false }
            )
        }
    }
}
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex322Week3RadioButtonDemoTheme {
        RadioButtonTShirtSize()
    }
}