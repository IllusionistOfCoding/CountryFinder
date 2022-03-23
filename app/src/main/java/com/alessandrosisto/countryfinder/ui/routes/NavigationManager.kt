package com.alessandrosisto.countryfinder.ui.routes

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.alessandrosisto.countryfinder.utilis.Type

/**
 * Destinations used in the [CountryFinderApp].
 */
object AppDestinations {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "detail"
    const val DIALOG_ROUTE = "dialog"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationManager(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(AppDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToDetail: (code: String) -> Unit = {
        navController.navigate(AppDestinations.DETAIL_ROUTE + "/$it") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val openDialog: (type: Type) -> Unit = {
        navController.navigate(AppDestinations.DIALOG_ROUTE + "/$it") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}