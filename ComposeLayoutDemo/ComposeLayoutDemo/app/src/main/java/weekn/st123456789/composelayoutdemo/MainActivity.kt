package weekn.st123456789.composelayoutdemo

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import weekn.st123456789.composelayoutdemo.ui.theme.ComposeLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLayoutDemoTheme {
                Scaffold( modifier = Modifier.fillMaxSize()

                ) { innerPadding ->

                    Greeting(
                        Message("On-line", "Classes"),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Message (val author: String, val body : String)

@Composable
fun Greeting(msg: Message, modifier: Modifier = Modifier) {
    //add all as a column
    Column {
        // Add padding around our message
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(top = 16.dp)
        ) { // adding 16 margin on the top
            Image(
                painter = painterResource(R.drawable.lesspon_via_zoom1),
                contentDescription = null,
                // Set image size to 60 dp
                modifier = Modifier
                    .size(60.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    // added border with material design colorschema
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 3.dp) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        scheduleBox()


    }
}


@Composable
fun scheduleBox(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
    ){
        Column {
            Text(
                text = "Schedule: ",
                color = Color.Black

            )
            Spacer(modifier = Modifier.width(8.dp)) // adding the horizontal space between the schedule and the inforamtion
        Text(
            text = "- Monday to Friday: 2 - 3 pm \n - Saturday: 11 am - noon ",
            color = Color.Black        )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun GreetingPreview() {
    ComposeLayoutDemoTheme {
        Greeting( Message(" Classes ", " on-line mode "))
    }
}
