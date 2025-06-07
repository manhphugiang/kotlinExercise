package weekn.st123456789.navigationwithviewmodelanddata

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreenTwo(navController: NavHostController, viewModel: MainViewModel) {
    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello, ${viewModel.userName}!", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { activity?.finish() }) {
            Text("Exit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel().apply { setUserName("John Doe") }
    ScreenTwo(navController, viewModel)
}
