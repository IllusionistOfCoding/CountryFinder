package com.alessandrosisto.countryfinder.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.alessandrosisto.countryfinder.repo.AppContainer
import com.alessandrosisto.countryfinder.ui.routes.ApolloTestNavGraph
import com.alessandrosisto.countryfinder.ui.routes.ApolloTestNavigationActions
import com.alessandrosisto.countryfinder.ui.theme.countryFinderTheme

@ExperimentalComposeUiApi
@Composable
fun CountryFinderApp(
    appContainer: AppContainer,
    isOnline: () -> Boolean
) {
    countryFinderTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                ApolloTestNavigationActions(navController)
            }
            ApolloTestNavGraph(
                appContainer = appContainer,
                navController = navController,
                navigationActions = navigationActions,
                isOnline = isOnline
            )
        }
    }
}