package com.alessandrosisto.countryfinder.ui.screens.detail

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alessandrosisto.countryfinder.R
import com.google.accompanist.insets.statusBarsPadding
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.ui.common.CountryFilterSnackbar
import com.alessandrosisto.countryfinder.ui.common.CountryFilterSnackbarHost
import com.alessandrosisto.countryfinder.ui.theme.*
import com.alessandrosisto.countryfinder.ui.common.FullScreenLoading
import com.alessandrosisto.countryfinder.ui.common.SingleEntrySection
import com.alessandrosisto.countryfinder.utilis.TEST_emoji_flag
import com.alessandrosisto.countryfinder.utilis.additionalInfo

@ExperimentalComposeUiApi
@Composable
fun DetailScreen(
    uiState: DetailUiState,
    onErrorDismiss: (Long) -> Unit,
    onBackPress: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
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
            Box(modifier = Modifier.statusBarsPadding()) {
                if (uiState.isLoading) {
                    FullScreenLoading()
                } else {
                    ContentDetailScreen(uiState = uiState)
                }
                DetailAppBar(onBackPress = onBackPress)
            }
        }
    }
    CountryFilterSnackbar(
        uiState = uiState,
        scaffoldState = scaffoldState,
        onErrorDismiss = onErrorDismiss
    )
}

@ExperimentalComposeUiApi
@Composable
fun ContentDetailScreen(uiState: DetailUiState) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = 16.dp))
            CountryNameSection(uiState.countryFeed)
            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
        uiState.countryFeed.capital?.let {
            item {
                SingleEntrySection("\uD83C\uDFDB️ capital", it)
            }
        }
        uiState.countryFeed.phone?.let {
            item {
                SingleEntrySection("\uD83D\uDCDE phone", "+$it")
            }
        }
        uiState.countryFeed.currency?.let {
            item {
                SingleEntrySection("\uD83D\uDCB5 currency", it)
            }
        }
        if (uiState.countryFeed.languages.isNotEmpty() &&
            uiState.countryFeed.languages.first().name.isNotEmpty()
        ) {
            item {
                SingleEntrySection(
                    label = "\uD83E\uDDCF languages",
                    text = uiState.countryFeed.languages.map {
                        it.name
                    }.reduce { acc, s -> "$acc, $s" })
            }
        }
        if (uiState.countryFeed.continent.code.isNotEmpty()) {
            item {
                ContinentMapSection(uiState.countryFeed)
                Spacer(modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ContinentMapSection(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = white20
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "\uD83C\uDF0E continent",
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
            )
            Text(
                text = country.continent.name,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontSize = 16.sp,
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.padding(16.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(country.additionalInfo()),
                contentDescription = stringResource(id = R.string.world_map)
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun CountryNameSection(country: Country) {
    val animAngle = rememberInfiniteTransition()
    val angle = animAngle.animateFloat(
        initialValue = 0f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .testTag(TEST_emoji_flag)
                .graphicsLayer(
                    transformOrigin = TransformOrigin(0.5f, 0.5f),
                    rotationY = -angle.value,
                    rotationX = angle.value,
                    cameraDistance = 16.dp.value
                ),
            text = country.emoji,
            fontSize = 180.sp,
        )
        Text(
            text = country.name,
            color = Color.White,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
        )
        country.native?.let { native ->
            Text(
                text = native,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.h2,
                fontSize = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun DetailAppBar(onBackPress: () -> Unit) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(
                transparent
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(24.dp)
                .width(24.dp),
            onClick = {
                Log.d("DetailAppBar", "BackPress")
                onBackPress()
            }) {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                imageVector = Icons.Outlined.ArrowBack,
                tint = Color.White,
                contentDescription = null,
            )
        }
    }
}

//@Preview("AppBar", widthDp = 360, heightDp = 64)
@Composable
fun PreviewAppBar() {
    DetailAppBar({})
}

@Preview("DetailScreen")
@ExperimentalComposeUiApi
@Composable
fun PreviewDetailScreen() {
    DetailScreen(DetailUiState(), {}, {})
}