package com.visualrecursion.slidenotes.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.visualrecursion.slidenotes.ui.navigation.NavRoute

fun NavController.isCurrentDestination(route: String): Boolean {
    val currentRoute = this.currentBackStackEntry?.destination?.route
    return currentRoute == route
}

@Composable
fun NavController.isCurrentDestinationOnCollectionId(collectionId: Long): Boolean {
    val currentState = this.currentBackStackEntryAsState()
    val currentDestinationCollectionId = currentState.value?.arguments?.getLong(NavRoute.Arguments.COLLECTION_ID)
    return collectionId == currentDestinationCollectionId
}