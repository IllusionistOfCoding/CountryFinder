package com.alessandrosisto.countryfinder.ui.screens.detail

import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.repo.fake.emptyCountry
import com.alessandrosisto.countryfinder.ui.common.UiState
import com.alessandrosisto.countryfinder.utilis.ErrorMessage

data class DetailUiState(
    val countryFeed: Country = emptyCountry,
    override val isLoading: Boolean = false,
    override val errorMessages: List<ErrorMessage> = emptyList(),
) : UiState