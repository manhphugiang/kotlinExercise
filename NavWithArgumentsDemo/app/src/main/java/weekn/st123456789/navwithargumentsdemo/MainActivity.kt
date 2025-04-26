package weekn.st123456789.navwithargumentsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import weekn.st123456789.navwithargumentsdemo.ui.theme.NavWithArgumentsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavWithArgumentsDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Nav( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Nav(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home" ){
        composable(route = "Home"){ HomeScreen(navController) }
        composable(
            // Route "Details" allows passing optional arguments (name and age)
             route = "Details?name={name}&age={age}",
            // the route below must passing MANDATORY arguments
            //route = "Details/{name}/{age}",
                    arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = "age"){
                    type = NavType.IntType
                    defaultValue= 0
                }
            )
        ){
           // retrieves the arguments passed to the route
            backstackEntry ->
            DetailsScreen(
                myName = backstackEntry.arguments?.getString("name") ,
                myAge = backstackEntry.arguments?.getInt("age")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavWithArgumentsDemoTheme {
        Nav()
    }
}