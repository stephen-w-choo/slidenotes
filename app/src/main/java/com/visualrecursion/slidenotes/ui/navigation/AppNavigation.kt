package com.visualrecursion.slidenotes.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.visualrecursion.slidenotes.ui.screens.landing.conversionMenu.ConversionMenuView
import com.visualrecursion.slidenotes.ui.screens.landing.conversionMenu.ConversionMenuViewModel
import com.visualrecursion.slidenotes.ui.screens.landing.startMenu.StartMenuView
import com.visualrecursion.slidenotes.ui.screens.landing.startMenu.StartMenuViewModel
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesView
import com.visualrecursion.slidenotes.ui.screens.notesView.NotesViewModel


sealed class NavRoute(val name: String) {
    object Arguments {
        const val COLLECTION_ID = "collectionId"
    }
    data object StartMenu : NavRoute("startMenu")
    data object ConversionMenu : NavRoute("conversionMenu")
    data object SlideNotesViewer : NavRoute("slideNotesViewer/{${Arguments.COLLECTION_ID}}") {
        fun routeFromId(collectionId: Long): String {
            return "slideNotesViewer/$collectionId"
        }
    }
}

fun NavHostController.navigateToCollectionWithId(collectionId: Long) {
    this.navigate(NavRoute.SlideNotesViewer.routeFromId(collectionId))
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldModifier: Modifier
) {
    // The conversion menu holds the Pptx object, which needs to be shared between routes
    // This is because we are otherwise unable to pass complex objects between viewModels and routes
    val conversionMenuViewModel = hiltViewModel<ConversionMenuViewModel>()

    NavHost(
        navController = navController,
        startDestination = NavRoute.StartMenu.name,
        modifier = scaffoldModifier
    ) {
        composable(
            route = NavRoute.StartMenu.name
        ) { backStackEntry ->
            StartMenuView(
                viewModel = hiltViewModel<StartMenuViewModel>(backStackEntry),
                navigateToConversionMenu = { navController.navigate(NavRoute.ConversionMenu.name) }
            )
        }
        composable(
            route = NavRoute.ConversionMenu.name
        ) {
            ConversionMenuView(
                viewModel = conversionMenuViewModel,
                navigateToNotesView = { collectionId ->
                    navController.navigateToCollectionWithId(collectionId)
                }
            )
        }
        composable(
            route = NavRoute.SlideNotesViewer.name,
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


