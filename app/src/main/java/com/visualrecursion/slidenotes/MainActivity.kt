package com.visualrecursion.slidenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.SlideNotesTheme
import com.visualrecursion.slidenotes.ui.navigation.AppNavigation
import com.visualrecursion.slidenotes.ui.navigation.NavigationDrawer
import com.visualrecursion.slidenotes.ui.navigation.ScaffoldViewModel
import com.visualrecursion.slidenotes.ui.navigation.TopBar
import com.visualrecursion.slidenotes.ui.theme.ThemeWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeWrapper { themeActions ->
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val scaffoldViewModel: ScaffoldViewModel by viewModels()
                val navController = rememberNavController()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        NavigationDrawer(
                            viewModel = scaffoldViewModel,
                            drawerState = drawerState,
                            navController = navController
                        )
                    },
                ) {
                    Scaffold(
                        topBar = { TopBar(
                            toggleNavDrawer = { scope.launch { drawerState.open() } },
                            themeActions = themeActions
                        ) },
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.background,
                    ) { innerPadding ->

                        val scaffoldModifier = Modifier
                            .padding(innerPadding)

                        AppNavigation(
                            navController = navController,
                            scaffoldModifier = scaffoldModifier
                        )
                    }
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