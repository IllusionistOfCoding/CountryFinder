package com.alessandrosisto.countryfinder.ui.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.repo.AppContainer
import com.alessandrosisto.countryfinder.ui.common.ListItemsDialog
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailScreen
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailViewModel
import com.alessandrosisto.countryfinder.ui.screens.home.HomeScreen
import com.alessandrosisto.countryfinder.ui.screens.home.HomeViewModel
import com.alessandrosisto.countryfinder.utilis.CONTINENT_TYPE
import com.alessandrosisto.countryfinder.utilis.toContinent
import com.alessandrosisto.countryfinder.utilis.toLanguage

@ExperimentalComposeUiApi
@Composable
fun ApolloTestNavGraph(
    appContainer: AppContainer,
    navigationActions: ApolloTestNavigationActions,
    isOnline: () -> Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ApolloTestDestinations.HOME_ROUTE
) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.provideFactory(countryRepository = appContainer.countryRepository)
    )
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = ApolloTestDestinations.HOME_ROUTE) {
            val uiState by homeViewModel.uiState.collectAsState()
            val scrollToTop by homeViewModel.scrollToTop.collectAsState()
            val wrapActionOpenDialog = homeViewModel.handleConnectionAction(
                isOnline = isOnline(),
                action = navigationActions.openDialog
            )
            val wrapActionNavigateToDetail = homeViewModel.handleConnectionAction(
                isOnline = isOnline(),
                action = navigationActions.navigateToDetail
            )

            HomeScreen(
                uiState = uiState,
                scrollToTop = scrollToTop,
                onErrorDismiss = { homeViewModel.errorShown(it) },
                updateScrollToTop = { homeViewModel.updateScrollToTop(it) },
                openDialog = wrapActionOpenDialog,
                navigateToDetail = wrapActionNavigateToDetail
            )
        }
        composable(
            route = ApolloTestDestinations.DETAIL_ROUTE + "/{code}",
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) { backStackEntry ->
            val detailViewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.provideFactory(
                    countryRepository = appContainer.countryRepository
                )
            )
            val code = backStackEntry.arguments?.getString("code") ?: "IT"
            detailViewModel.fetchCountry(code)
            val uiState by detailViewModel.uiState.collectAsState()

            DetailScreen(
                uiState = uiState,
                onErrorDismiss = { detailViewModel.errorShown(it) },
                onBackPress = navController::popBackStack

            )
        }
        dialog(route = ApolloTestDestinations.DIALOG_ROUTE + "/{type}") { backStackEntry ->
            val uiState by homeViewModel.uiState.collectAsState()
            val type = backStackEntry.arguments?.getString("type") ?: CONTINENT_TYPE
            val entryList = if (type == CONTINENT_TYPE) {
                uiState.allContinents
            } else {
                uiState.allLanguages
            }
            val entrySelected = if (type == CONTINENT_TYPE) {
                uiState.selectedContinent
            } else {
                uiState.selectedLanguage
            }
            val onItemSelected: (EntryDialog) -> Unit = if (type == CONTINENT_TYPE) {
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