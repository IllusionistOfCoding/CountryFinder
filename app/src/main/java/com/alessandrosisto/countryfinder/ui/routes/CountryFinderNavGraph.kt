package com.alessandrosisto.countryfinder.ui.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.ui.components.ListItemsDialog
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailScreen
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailViewModel
import com.alessandrosisto.countryfinder.ui.screens.home.HomeScreen
import com.alessandrosisto.countryfinder.ui.screens.home.HomeViewModel
import com.alessandrosisto.countryfinder.utilis.Type
import com.alessandrosisto.countryfinder.utilis.toContinent
import com.alessandrosisto.countryfinder.utilis.toLanguage

@ExperimentalComposeUiApi
@Composable
fun CountryFinderNavGraph(
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AppDestinations.HOME_ROUTE) {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            val uiState by homeViewModel.uiState.collectAsState()
            val scrollToTop by homeViewModel.scrollToTop.collectAsState()

            HomeScreen(
                uiState = uiState,
                scrollToTop = scrollToTop,
                onErrorDismiss = { homeViewModel.errorShown(it) },
                updateScrollToTop = { homeViewModel.updateScrollToTop(it) },
                openDialog = navigationManager.openDialog,
                navigateToDetail = navigationManager.navigateToDetail
            )
        }
        composable(
            route = AppDestinations.DETAIL_ROUTE + "/{code}",
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) {
            val detailViewModel = hiltViewModel<DetailViewModel>()
            val uiState by detailViewModel.uiState.collectAsState()

            DetailScreen(
                uiState = uiState,
                onErrorDismiss = { detailViewModel.errorShown(it) },
                onBackPress = navController::popBackStack

            )
        }
        dialog(
            route = AppDestinations.DIALOG_ROUTE + "/{type}",
            arguments = listOf(navArgument("type") { type = NavType.EnumType(Type::class.java) })
        ) { backStackEntry ->
            // TODO find new approach
            val parentEntry = remember {
                navController.getBackStackEntry(AppDestinations.HOME_ROUTE)
            }
            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            val uiState by homeViewModel.uiState.collectAsState()
            val type: Type = backStackEntry.arguments?.getSerializable("type") as? Type ?: Type.Continent
            val entryList = if (type == Type.Continent) {
                uiState.allContinents
            } else {
                uiState.allLanguages
            }
            val entrySelected = if (type == Type.Continent) {
                uiState.selectedContinent
            } else {
                uiState.selectedLanguage
            }
            val onItemSelected: (EntryDialog) -> Unit = if (type == Type.Continent) {
                { entry ->
                    navController.popBackStack()
                    homeViewModel.selectContinent(entry.toContinent())
                }
            } else {
                { entry ->
                    navController.popBackStack()
                    homeViewModel.selectLanguage(entry.toLanguage())
                }
            }

            ListItemsDialog(
                list = entryList,
                type = type,
                selectedItem = entrySelected,
                onItemSelected = onItemSelected,
                onDismissDialog = navController::popBackStack
            )
        }
    }
}