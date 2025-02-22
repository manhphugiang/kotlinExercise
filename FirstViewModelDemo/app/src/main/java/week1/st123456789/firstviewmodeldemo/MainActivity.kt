package week1.st123456789.firstviewmodeldemo

/* Demo program to show how to use ViewModel :

the program serves as a foundational example of managing state with a ViewModel,
and implementing basic hardcoded user management functionality.

 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import week1.st123456789.firstviewmodeldemo.ui.theme.FirstViewModelDemoTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstViewModelDemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("User List") })
                    }
                ) { paddingValues ->
                    UserScreen(userViewModel, Modifier.padding(paddingValues))
                }
              }
        }
    }
}

@Composable
fun UserScreen(viewModel: UserViewModel, modifier: Modifier = Modifier) {

    val users = viewModel.users

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding around the entire column
    )
    {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Your Name") },
            modifier = Modifier.fillMaxWidth()

        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Your Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {

                val id = users.size + 1

                viewModel.addUser(id, name, email)

                name = ""
                email = ""
            },
        )
        {
            Text("Add User")
        }

        users.forEach { user ->
            Text(text = "User : ${user.name}")
            Modifier.padding(top = 5.dp)
            Text(text = "User Email : ${user.email}")
            Modifier.padding(top = 25.dp)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    FirstViewModelDemoTheme {
        UserScreen(viewModel = UserViewModel())
    }
}