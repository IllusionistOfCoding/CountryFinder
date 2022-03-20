package com.alessandrosisto.countryfinder.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.alessandrosisto.countryfinder.repo.AppContainer
import com.alessandrosisto.countryfinder.ui.routes.CountryFinderNavGraph
import com.alessandrosisto.countryfinder.ui.routes.CountryFinderNavigationActions
import com.alessandrosisto.countryfinder.ui.theme.CountryFinderTheme
import com.alessandrosisto.countryfinder.utilis.isOnline
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalComposeUiApi
@Composable
fun CountryFinderApp(
    appContainer: AppContainer
) {
    CountryFinderTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                CountryFinderNavigationActions(navController)
            }
            CountryFinderNavGraph(
                appContainer = appContainer,
                navController = navController,
                navigationActions = navigationActions,
                isOnline = appContainer.context::isOnline
            )
        }
    }
}