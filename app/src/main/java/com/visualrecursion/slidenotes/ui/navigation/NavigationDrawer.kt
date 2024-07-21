package com.visualrecursion.slidenotes.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.visualrecursion.slidenotes.R
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
        NavigationDrawerItem(
            label = {
                Text(text = stringResource(R.string.make_a_new_slidenote))
            },
            selected = false,
            onClick = {
                navController.navigate(NavRoute.StartMenu.name)
                closeDrawer()
            }
        )

        collectionEntitiesList.map {
            NavigationDrawerItem(
                label = {
                    Row {
                        Text(text = it.name)
                        Button(onClick = { viewModel.deleteEntityById(it.id) }) {
                            Text(text = "Delete")
                        }
                    }
                },
                selected = false,
                onClick = {
                    navController.navigateToCollectionWithId(it.id)
                    closeDrawer()
                }
            )
        }
    }
}