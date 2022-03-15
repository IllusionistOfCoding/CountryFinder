package com.alessandrosisto.countryfinder.ui.common

import com.alessandrosisto.countryfinder.utilis.ErrorMessage

interface UiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
}