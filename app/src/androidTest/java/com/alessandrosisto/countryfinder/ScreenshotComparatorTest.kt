package com.alessandrosisto.countryfinder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.repo.fake.fakeAllCountriesInNA
import com.alessandrosisto.countryfinder.repo.fake.fakeDetailCountry
import com.alessandrosisto.countryfinder.repo.fake.fakeEntryDialog
import com.alessandrosisto.countryfinder.ui.common.ListItemsDialog
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailScreen
import com.alessandrosisto.countryfinder.ui.screens.detail.DetailUiState
import com.alessandrosisto.countryfinder.ui.screens.home.HomeScreen
import com.alessandrosisto.countryfinder.ui.screens.home.HomeUiState
import com.alessandrosisto.countryfinder.ui.theme.CountryFinderTheme
import org.junit.Rule
import org.junit.Test
import java.io.FileOutputStream

/**
 * Screenshot Tests
 * The first time we take a screenshot of the screen, by running the test and letting it fail,
 * because each testing device has a different size screen.
 * This creates a screenshot for us, present inside the app’s cache folder.
 * the screenshot captured by the test and compare it with the asset image, which we saved on
 * the initial run of this test
 */
class ScreenshotComparatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_screenshot_HomeScreen() {
        composeTestRule.setContent {
            CountryFinderTheme {
                HomeScreen(
                    uiState = HomeUiState(countriesFeed = fakeAllCountriesInNA),
                    scrollToTop = false,
                    onErrorDismiss = {},
                    updateScrollToTop = {},
                    openDialog = {},
                    navigateToDetail = {}
                )
            }
        }
        assertScreenshotMatchesGolden("home_screen", composeTestRule.onRoot())
    }
    @ExperimentalComposeUiApi
    @Test
    fun test_screenshot_DetailScreen() {
        composeTestRule.setContent {
            CountryFinderTheme {
                DetailScreen(uiState = DetailUiState(fakeDetailCountry), {}) { }
            }
        }
        assertScreenshotMatchesGolden("detail_screen", composeTestRule.onRoot())
    }

    @Test
    fun test_screenshot_ListItemsDialog() {
        composeTestRule.setContent {
            CountryFinderTheme {
                ListItemsDialog(fakeEntryDialog, "Languages", EntryDialog(code = "af"), {}, {})
            }
        }
        assertScreenshotMatchesGolden("list_items_dialog", composeTestRule.onRoot())
    }


    /**
     * We capture the current screen as a bitmap, and then we save the image as a file with a name
     * and current timestamp in milliseconds. This file is saved under the app’s cache folder:
     * /data/data/com.alessandrosisto.countryfinder/file/[goldenName].png
     * To recover it go to View/Tool Windows/Device File Explorer/
     * @param goldenName name of the file generate and compare
     * @param node represents a semantics node and the path to fetch it from the semantics tree
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun assertScreenshotMatchesGolden(
        goldenName: String,
        node: SemanticsNodeInteraction
    ) {
        val bitmap = node.captureToImage().asAndroidBitmap()

        // Save screenshot to file for debugging
        saveScreenshot(goldenName + System.currentTimeMillis().toString(), bitmap)
        val golden = InstrumentationRegistry.getInstrumentation()
            .context.resources.assets.open("$goldenName.png").use { BitmapFactory.decodeStream(it) }

        golden.compare(bitmap)
    }

    private fun saveScreenshot(filename: String, bmp: Bitmap) {
        val path = InstrumentationRegistry.getInstrumentation().targetContext.filesDir.canonicalPath
        FileOutputStream("$path/$filename.png").use { out ->
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        println("Saved screenshot to $path/$filename.png")
    }

    private fun Bitmap.compare(other: Bitmap) {
        if (this.width != other.width || this.height != other.height) {
            throw AssertionError("Size of screenshot does not match golden file (check device density)")
        }
        // Compare row by row to save memory on device
        val row1 = IntArray(width)
        val row2 = IntArray(width)
        for (column in 0 until height) {
            // Read one row per bitmap and compare
            this.getRow(row1, column)
            other.getRow(row2, column)
            if (!row1.contentEquals(row2)) {
                throw AssertionError("Sizes match but bitmap content has differences")
            }
        }
    }

    private fun Bitmap.getRow(pixels: IntArray, column: Int) {
        this.getPixels(pixels, 0, width, 0, column, width, 1)
    }
}