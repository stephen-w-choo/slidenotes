package com.visualrecursion.slidenotes.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.visualrecursion.slidenotes.ui.screens.landing.StartMenuView
import com.visualrecursion.slidenotes.ui.screens.landing.StartMenuViewModel
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesView
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesViewModel


sealed class NavRoute(val name: String) {
    object Arguments {
        const val COLLECTION_ID = "collectionId"
    }
    data object StartMenu : NavRoute("startMenu")
    data object NotesCarousel : NavRoute("notesCarousel/{${Arguments.COLLECTION_ID}}") {
        fun retrieveCollectionWithId(collectionId: Long): String = "notesCarousel/$collectionId"
    }
}



@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldModifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.StartMenu.name,
        modifier = scaffoldModifier
    )  {
        composable(
            route = NavRoute.StartMenu.name
        ) { backStackEntry ->
            StartMenuView(
                viewModel = hiltViewModel<StartMenuViewModel>(backStackEntry),
                navigateToNotes = { collectionId ->
                    navController.navigate(
                        NavRoute.NotesCarousel.retrieveCollectionWithId(collectionId)
                    )
                }
            )
        }
        composable(
            route = NavRoute.NotesCarousel.name,
            arguments = listOf(
                navArgument(NavRoute.Arguments.COLLECTION_ID) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            NotesView(
                viewModel = hiltViewModel<NotesViewModel>(backStackEntry),
                navigateBackToStart = { navController.navigate(NavRoute.StartMenu.name) }
            )
        }
    }
}


