package com.alessandrosisto.countryfinder.ui.common

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alessandrosisto.countryfinder.ui.screens.UiState
import com.google.accompanist.insets.systemBarsPadding

/**
 * [SnackbarHost] that is configured for insets and large screens
 */
@Composable
fun CountryFilterSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
            .systemBarsPadding()
            .wrapContentWidth(align = Alignment.Start)
            .widthIn(max = 550.dp),
        snackbar = snackbar
    )
}

/**
 * Process one error message at a time and show them as [CountryFilterSnackbarHost] in the UI
 * Get the text to show on the message from resources, if onRefreshPosts or onErrorDismiss change
 * while the LaunchedEffect is running, don't restart the effect and use the latest lambda values.
 * Effect running in a coroutine that displays the Snackbar on the screen
 * If there's a change to errorMessageText, retryMessageText or scaffoldState, the previous effect
 * will be cancelled and a new one will start with the new values
 */
@Composable
fun CountryFilterSnackbar(
    uiState: UiState,
    scaffoldState: ScaffoldState,
    onErrorDismiss: (Long) -> Unit
) {
    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

        LaunchedEffect(errorMessageText, scaffoldState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessageText,
            )
            onErrorDismissState(errorMessage.id)
        }
    }
}