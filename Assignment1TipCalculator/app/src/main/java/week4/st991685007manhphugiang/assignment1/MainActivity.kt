package week4.st991685007manhphugiang.assignment1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import week4.st991685007manhphugiang.assignment1.ui.theme.Assignment1TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment1TipCalculatorTheme {
                TipCalculator(modifier = Modifier.padding(24.dp))
            }
        }
    }
}


@Composable
fun TipCalculator(modifier: Modifier = Modifier) {
    var amount by remember { mutableStateOf("") }
    val tipOptions = listOf("10%", "15%", "20%", "Other")
    var selectedOption by remember { mutableStateOf(tipOptions[0]) }

    var customTip by remember { mutableStateOf("") }
    var numberPeople by remember { mutableStateOf("") }
    var totalPerPerson by remember { mutableStateOf("") }
    var tipTotalAmount by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("") }
    var perPerson by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Tip Calculator",
            modifier = modifier
        )

        Spacer(modifier = Modifier.height(25.dp)) // space between TextField and text
        Column {

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                modifier = Modifier.padding(10.dp),
                label = { Text("Amount: ") }
            )

            Text("Select Tip Percentage")

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { expanded = true },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(selectedOption)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    tipOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = {
                            selectedOption = option
                            if (option != "Other") {
                                customTip = ""
                            }
                            expanded = false
                        })
                    }
                }
            }
            // showing the custom tip text box
            if (selectedOption == "Other") {
                OutlinedTextField(
                    value = customTip,
                    onValueChange = { customTip = it },
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    label = { Text("Enter Custom Tip %") },
                )
            }

            OutlinedTextField(
                value = numberPeople,
                onValueChange = { numberPeople = it },
                modifier = Modifier.padding(10.dp),
                label = { Text("Number of People : ") }
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
            ) {
                Button(onClick = {
                    val amountValue = amount.toDoubleOrNull()
                    val tipValue = if (selectedOption == "Other" && customTip.isNotEmpty()) customTip.toDoubleOrNull() else selectedOption.dropLast(1).toDoubleOrNull()
                    val peopleCount = numberPeople.toIntOrNull() ?: 1


                    if (amountValue == null) {
                        Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                    }
                    if (selectedOption == "Other" && (tipValue == null || tipValue < 0)) {
                        Toast.makeText(context, "Please enter a valid tip percentage", Toast.LENGTH_SHORT).show()
                    }

                    // these tip amount and tip value can be null
                    val tipAmount = (amountValue ?: 0.0) * (tipValue ?: 0.0) / 100
                    val calculatedTotalAmount = (amountValue ?: 0.0) + tipAmount


                    perPerson = if (peopleCount > 1) {
                        String.format("%.2f", calculatedTotalAmount / peopleCount)
                    } else {
                        ""
                    }

                    totalPerPerson = if (peopleCount > 1) {
                        "Total per person: $$perPerson"
                    } else {
                        ""
                    }

                    tipTotalAmount = "Tip Total Amount: $" + String.format("%.2f", tipAmount)

                    totalAmount = "Total Amount: $" + String.format("%.2f", calculatedTotalAmount)
                }) {
                    Text("Calculate")
                }

                Button(onClick = {
                    amount = ""
                    customTip = ""
                    numberPeople = "1" // resets the # people spinner to 1 person
                    selectedOption = tipOptions[0]
                    totalPerPerson = ""
                    tipTotalAmount = ""
                    totalAmount = ""
                    perPerson = ""
                }) {
                    Text("Clear")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (tipTotalAmount.isNotEmpty()) Text(text = tipTotalAmount, modifier = Modifier.padding(10.dp))
            if (totalAmount.isNotEmpty()) Text(text = totalAmount, modifier = Modifier.padding(10.dp))
            if (totalPerPerson.isNotEmpty()) Text(text = totalPerPerson, modifier = Modifier.padding(10.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment1TipCalculatorTheme {
        TipCalculator()
    }
}