package com.visualrecursion.slidenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.SlideNotesTheme
import com.visualrecursion.slidenotes.ui.navigation.AppNavigation
import com.visualrecursion.slidenotes.ui.navigation.TopBar
import com.visualrecursion.slidenotes.ui.theme.ThemeWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeWrapper { themeActions ->
                Scaffold(
                    topBar = { TopBar(themeActions) },
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                ) { innerPadding ->
                    val scaffoldModifier = Modifier
                        .padding(innerPadding)
                    val navController = rememberNavController()

                    AppNavigation(
                        navController = navController,
                        scaffoldModifier = scaffoldModifier
                    )
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlideNotesTheme {
        Greeting("Android")
    }
}