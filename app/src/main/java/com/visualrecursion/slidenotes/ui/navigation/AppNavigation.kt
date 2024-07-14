package com.visualrecursion.slidenotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.visualrecursion.slidenotes.ui.screens.startMenu.StartMenuView
import com.visualrecursion.slidenotes.ui.screens.startMenu.StartMenuViewModel
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesView
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesViewModel


sealed class NavRoute(val name: String) {
    data object StartMenu : NavRoute("startMenu")
    data object Notes : NavRoute("notes")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldModifier: Modifier
) {
    // TODO - move back into composable
    // Temporarily brought out to act as a shared viewModel until the database is done
    val notesViewModel = hiltViewModel<NotesViewModel>()


    NavHost(
        navController = navController,
        startDestination = NavRoute.StartMenu.name,
        modifier = scaffoldModifier
    )  {
        composable(NavRoute.StartMenu.name) {
            val viewModel = hiltViewModel<StartMenuViewModel>()
            StartMenuView(
                viewModel = viewModel,
                notesViewModel = notesViewModel,
                navigateToNotes = { slideData ->
                    navController.navigate(NavRoute.Notes.name)
                }
            )
        }
        composable(NavRoute.Notes.name) {
            NotesView(viewModel = notesViewModel)
        }
    }
}


