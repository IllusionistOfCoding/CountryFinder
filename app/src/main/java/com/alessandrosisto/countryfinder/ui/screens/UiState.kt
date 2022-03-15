package com.alessandrosisto.countryfinder.ui.screens

import com.alessandrosisto.countryfinder.utilis.ErrorMessage

interface UiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
}