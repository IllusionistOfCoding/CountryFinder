package com.alessandrosisto.countryfinder.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alessandrosisto.countryfinder.ui.theme.white20
import com.alessandrosisto.countryfinder.utilis.TEST_progress_bar

@Composable
fun SingleEntrySection(label: String, text: String) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = white20
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h1,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun MultiEntrySection(label: String, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = white20
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        ) {
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.h1,
                fontSize = 16.sp,
            )
        }
    }
}

/**
 * Full screen circular progress indicator
 */
@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .testTag(TEST_progress_bar)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

/**
 * Full screen text message
 */
@Composable
fun FullScreenMessage(@StringRes textResId: Int) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = textResId)
        )
    }
}