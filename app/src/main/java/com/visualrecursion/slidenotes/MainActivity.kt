package com.visualrecursion.slidenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.visualrecursion.slidenotes.domain.ConvertPptUseCase
import com.visualrecursion.slidenotes.ui.theme.SlideNotesTheme
import dagger.hilt.android.AndroidEntryPoint
import org.visualrecursion.slidenotes.view.FileConversionView
import org.visualrecursion.slidenotes.view.FileConversionViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: FileConversionViewModel by viewModels()
                    val scaffoldModifier = Modifier.padding(innerPadding)

                    FileConversionView(
                        viewModel = viewModel,
                        modifier = scaffoldModifier,
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