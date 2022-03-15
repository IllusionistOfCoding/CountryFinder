package com.alessandrosisto.countryfinder.ui.screens.home

import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.ui.common.UiState
import com.alessandrosisto.countryfinder.utilis.CONTINENT_TYPE
import com.alessandrosisto.countryfinder.utilis.ErrorMessage
import com.alessandrosisto.countryfinder.utilis.LANGUAGE_TYPE
import com.alessandrosisto.countryfinder.utilis.NONE_CODE

data class HomeUiState(
    val countriesFeed: List<Country> = emptyList(),
    val allContinents: List<EntryDialog> = emptyList(),
    val allLanguages: List<EntryDialog> = emptyList(),
    val selectedContinent: EntryDialog = EntryDialog(type = CONTINENT_TYPE, code = NONE_CODE),
    val selectedLanguage: EntryDialog = EntryDialog(type = LANGUAGE_TYPE, code = NONE_CODE),
    override val isLoading: Boolean = false,
    override val errorMessages: List<ErrorMessage> = emptyList(),
): UiState