package com.alessandrosisto.countryfinder.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.di.MainDispatcher
import com.alessandrosisto.countryfinder.repo.ICountryRepository
import com.alessandrosisto.countryfinder.utilis.ErrorMessage
import com.alessandrosisto.countryfinder.utilis.Result
import com.alessandrosisto.countryfinder.utilis.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val countryRepository: ICountryRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var cachedCode = ""

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val uiState = _detailUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _detailUiState.value
    )

    fun fetchCountry(code: String) {
        if (cachedCode != code) {
            cachedCode = code

            _detailUiState.update { it.copy(isLoading = true) }
            viewModelScope.launch(dispatcher) {
                val result = countryRepository.getCountry(code)
                _detailUiState.update {
                    when (result) {
                        is Result.Success -> {
                            log("fetchCountry : ${result.data.name}", "MAIN_FLOW")
                            it.copy(countryFeed = result.data, isLoading = false)
                        }
                        is Result.Error -> {
                            val errorMessages = it.errorMessages + ErrorMessage(
                                id = UUID.randomUUID().mostSignificantBits,
                                messageId = R.string.generic_error
                            )
                            it.copy(errorMessages = errorMessages, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    /**
     * Notify that an error was displayed on the screen ed remove from state [errorMessages]
     */
    fun errorShown(errorId: Long) {
        _detailUiState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}