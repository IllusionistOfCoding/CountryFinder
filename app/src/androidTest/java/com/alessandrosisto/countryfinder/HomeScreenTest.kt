package com.alessandrosisto.countryfinder

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.alessandrosisto.countryfinder.repo.fake.fakeAllCountries
import com.alessandrosisto.countryfinder.ui.screens.home.HomeScreen
import com.alessandrosisto.countryfinder.ui.screens.home.HomeUiState
import com.alessandrosisto.countryfinder.utilis.TEST_button_filter
import com.alessandrosisto.countryfinder.utilis.TEST_card_country
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    private lateinit var appName: String
    private val firstCardCountry = fakeAllCountries.first()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            appName = stringResource(id = R.string.app_name)
            HomeScreen(
                uiState = HomeUiState(countriesFeed = fakeAllCountries),
                scrollToTop = false,
                onErrorDismiss = {},
                updateScrollToTop = {},
                openDialog = {},
                navigateToDetail = {}
            )
        }
    }

    @Test
    fun app_displayed_topBar_app_name() {
        composeTestRule.onNodeWithText(appName, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun app_displayed_button_filter() {
        val button = composeTestRule.onNode(hasTestTag(TEST_button_filter), useUnmergedTree = true)
        button.assertIsDisplayed()
    }

    @Test
    fun app_displayed_first_card_country() {
        composeTestRule.onNodeWithText(firstCardCountry.emoji, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(firstCardCountry.name, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(
            firstCardCountry.languages.first().name,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    @Test
    fun app_count_countries_exists() {
        val cards =
            composeTestRule.onAllNodes(hasTestTag(TEST_card_country), useUnmergedTree = true)
        cards.assertCountEquals(fakeAllCountries.size)
    }
}