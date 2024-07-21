package com.visualrecursion.slidenotes.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    viewModel: ScaffoldViewModel,
    drawerState: DrawerState,
    navController: NavHostController
) {
    val collectionEntitiesList = viewModel.navDrawerUiState.collectAsState().value
    val scope = rememberCoroutineScope()

    ModalDrawerSheet {
        collectionEntitiesList.map {
            NavigationDrawerItem(
                label = { Text(text = it.name) },
                selected = false,
                onClick = {
                    navController.navigateToCollectionWithId(it.id)
                    scope.launch { drawerState.close() }
                }
            )
        }
    }
}