package com.alessandrosisto.countryfinder.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.ui.common.*
import com.alessandrosisto.countryfinder.ui.theme.*
import com.google.accompanist.insets.statusBarsPadding
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.repo.fake.fakeCountry
import com.alessandrosisto.countryfinder.utilis.*

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    scrollToTop: Boolean,
    onErrorDismiss: (Long) -> Unit,
    updateScrollToTop: (Boolean) -> Unit,
    openDialog: (Type) -> Unit,
    navigateToDetail: (String) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    // We get to preserve the scroll throughout any changes to the content.
    val homeListLazyListState = rememberLazyListState()

    LaunchedEffect(
        key1 = scrollToTop,
    ) {
        if (scrollToTop) {
            homeListLazyListState.animateScrollToItem(0)
            updateScrollToTop(false)
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            CountryFilterSnackbarHost(hostState = it, snackbar = { data ->
                Snackbar(
                    backgroundColor = bgDialog,
                    contentColor = lightPrimaryColor,
                    snackbarData = data
                )
            })
        },
        topBar = {
            HomeTopAppBar()
        },
        modifier = Modifier
            .background(darkPrimaryColor)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            bgPrimaryColor,
                            bgSecondaryColor
                        ),
                    )
                )
        ) {
            if (uiState.isLoading) {
                FullScreenLoading()
            } else {
                if (uiState.countriesFeed.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(
                            top = 128.dp, start = 16.dp, end = 16.dp, bottom = 16.dp
                        ),
                        state = homeListLazyListState
                    ) {
                        items(uiState.countriesFeed) { item: Country ->
                            CardCountry(item) {
                                navigateToDetail(item.code)
                            }
                        }
                    }
                } else {
                    FullScreenMessage(R.string.empty_countries)
                }
            }
            FilterSection(
                uiState.selectedContinent,
                uiState.selectedLanguage,
                openDialog
            )
        }
    }
    CountryFilterSnackbar(
        uiState = uiState,
        scaffoldState = scaffoldState,
        onErrorDismiss = onErrorDismiss
    )
}

@Composable
fun FilterSection(
    selectedContinent: EntryDialog,
    selectedLanguage: EntryDialog,
    openDialog: (Type) -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    0.0f to bgPrimaryColor,
                    0.9f to bgPrimaryColor,
                    1.0f to transparent,
                )
            )
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    color = dividerColor,
                    text = selectedContinent.type.typeToDisplayTitle().lowercase()
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                ButtonFilter(
                    modifier = Modifier
                        .testTag(TEST_button_filter)
                        .fillMaxWidth()
                        .height(48.dp), selectedContinent, openDialog
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    color = dividerColor,
                    text = selectedLanguage.type.typeToDisplayTitle().lowercase()
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                ButtonFilter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp), selectedLanguage, openDialog
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            color = dividerColor,
            thickness = 2.dp
        )
    }

}

/**
 * TopAppBar for the Home screen
 */
@Composable
private fun HomeTopAppBar() {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                text = title,
                color = textColor,
            )
        },
        backgroundColor = darkPrimaryColor,
        elevation = 0.dp
    )
}

@Composable
fun CardCountry(
    country: Country,
    navigateToDetail: (Country) -> Unit
) {
    Card(
        modifier = Modifier
            .testTag(TEST_card_country)
            .height(110.dp)
            .fillMaxWidth()
            .clickable {
                navigateToDetail(country)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = transparent
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Fit,
            painter = painterResource(country.additionalInfo()),
            contentDescription = null,
            alpha = 0.6f
        )
        Row(
            Modifier
                .fillMaxSize()
                .background(
                    color = white20
                )
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = country.emoji,
                fontSize = 40.sp,
            )
            Row() {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "country",
                        color = textColor,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = country.name,
                        color = textColor,
                        fontSize = 18.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "language",
                        color = textColor,
                        fontSize = 14.sp,
                    )
                    if (country.languages.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(top = 2.dp),
                            text = country.languages.map { it.name }.reduce { acc, s ->
                                "$acc, $s"
                            },
                            color = textColor,
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(country: Country = fakeCountry) {
    CountryFinderTheme {
        HomeScreen(HomeUiState(), false, {}, {}, {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun CardCountryPreview(country: Country = fakeCountry) {
    CountryFinderTheme {
        CardCountry(country, {})
    }
}