package com.bhavnathacker.composebasicsworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhavnathacker.composebasicsworkshop.ui.theme.ComposeBasicsWorkshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsWorkshopTheme {
                var shouldShowWelcome by remember { mutableStateOf(true)}
                if (shouldShowWelcome) {
                    WelcomeScreen { shouldShowWelcome = false}
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onWelcomeClick: () -> Unit) {
    Button(onClick = onWelcomeClick) {
        Text(text = "Welcome!")
    }
}

@Composable
fun MainScreen(names: List<String> = listOf("Bhavna", "Thacker")) {
    Column {
        for (name in names) {
            var isClicked by remember { mutableStateOf(false) }

            val btnText = if (isClicked) "Clicked!" else "Click Me!"
            val btnColor = if (isClicked) MaterialTheme.colors.secondary else MaterialTheme.colors.primary

            Column {
                Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.hello), contentDescription = "Hello Image",
                        modifier = Modifier.size(24.dp))
                    Surface(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small) {
                        Text(text = "Hello $name", color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.body1)
                    }
                }

                OutlinedButton(onClick = { isClicked = !isClicked}, modifier = Modifier.padding(8.dp)) {
                    Text(text = btnText, color = btnColor)
                }
                
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    ComposeBasicsWorkshopTheme {
        MainScreen()
    }
}