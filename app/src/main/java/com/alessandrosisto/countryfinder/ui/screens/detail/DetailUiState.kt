package com.alessandrosisto.countryfinder.ui.screens.detail

import com.alessandrosisto.countryfinder.repo.fake.emptyCountry
import com.alessandrosisto.countryfinder.ui.screens.UiState
import com.alessandrosisto.countryfinder.utilis.ErrorMessage
import fragment.CountryFragment

data class DetailUiState(
    val countryFeed: CountryFragment = emptyCountry,
    override val isLoading: Boolean = false,
    override val errorMessages: List<ErrorMessage> = emptyList(),
) : UiState