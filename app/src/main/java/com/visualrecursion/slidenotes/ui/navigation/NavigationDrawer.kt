package com.visualrecursion.slidenotes.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.visualrecursion.slidenotes.ui.navigation.components.MakeNewSlideNoteButton
import com.visualrecursion.slidenotes.ui.navigation.components.SlideNoteNavDrawerItem
import com.visualrecursion.slidenotes.ui.utils.isCurrentDestinationOnCollectionId
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    viewModel: ScaffoldViewModel,
    drawerState: DrawerState,
    navController: NavHostController
) {
    val collectionEntitiesList = viewModel.navDrawerUiState.collectAsState(emptyList()).value
    val scope = rememberCoroutineScope()
    val closeDrawer = { scope.launch { drawerState.close() } }

    ModalDrawerSheet {
        MakeNewSlideNoteButton(
            onClick = {
                navController.navigate(NavRoute.StartMenu.name)
                closeDrawer()
            }
        )

        collectionEntitiesList.map {
            SlideNoteNavDrawerItem(
                slideNoteEntity = it,
                selected = navController.isCurrentDestinationOnCollectionId(collectionId = it.id),
                onDeleteAction = { viewModel.deleteEntityById(it.id) },
                onClickAction = {
                    navController.navigateToCollectionWithId(it.id)
                    closeDrawer()
                }
            )
        }
    }
}