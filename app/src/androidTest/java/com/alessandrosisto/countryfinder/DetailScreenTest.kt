package com.alessandrosisto.countryfinder

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.alessandrosisto.countryfinder.repo.fake.fakeDetailCountry
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailScreen
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailUiState
import com.alessandrosisto.countryfinder.utilis.TEST_emoji_flag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    private val testCountry = fakeDetailCountry
    private lateinit var worldString: String

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalComposeUiApi
    @Before
    fun setUp() {
        composeTestRule.setContent {
            worldString = stringResource(id = R.string.world_map)
            DetailScreen(uiState = DetailUiState(testCountry), {}, {})
        }
    }

    @ExperimentalComposeUiApi
    @Test
    fun app_displayed_name_country_and_native() {
        composeTestRule.onNodeWithText(testCountry.name, useUnmergedTree = true).assertIsDisplayed()
        val native = testCountry.native ?: ""
        composeTestRule.onNodeWithText(native, useUnmergedTree = true).assertIsDisplayed()
    }

    @ExperimentalComposeUiApi
    @Test
    fun app_displayed_country_emoji_flag() {
        composeTestRule.onNode(hasTestTag(TEST_emoji_flag), useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @ExperimentalComposeUiApi
    @Test
    fun app_exists_entry_sections() {
        composeTestRule.onNodeWithText(testCountry.capital ?: "", useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithText(testCountry.currency ?: "", useUnmergedTree = true)
            .assertExists()
        val phone = "+${testCountry.phone ?: ""}"
        composeTestRule.onNodeWithText(phone, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText(testCountry.continent.name, useUnmergedTree = true)
            .assertExists()
    }

    @ExperimentalComposeUiApi
    @Test
    fun app_displayed_continent_map() {
        composeTestRule.onNodeWithContentDescription(worldString, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}