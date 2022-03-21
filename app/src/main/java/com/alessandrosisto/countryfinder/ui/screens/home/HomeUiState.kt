package com.alessandrosisto.countryfinder.ui.screens.home


import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.ui.screens.UiState
import com.alessandrosisto.countryfinder.utilis.ErrorMessage
import com.alessandrosisto.countryfinder.utilis.NONE_CODE
import com.alessandrosisto.countryfinder.utilis.Type
import fragment.CountryFragment

data class HomeUiState(
    val countriesFeed: List<CountryFragment> = emptyList(),
    val allContinents: List<EntryDialog> = emptyList(),
    val allLanguages: List<EntryDialog> = emptyList(),
    val selectedContinent: EntryDialog = EntryDialog(type = Type.Continent, code = NONE_CODE),
    val selectedLanguage: EntryDialog = EntryDialog(type = Type.Language, code = NONE_CODE),
    override val isLoading: Boolean = false,
    override val errorMessages: List<ErrorMessage> = emptyList(),
): UiState