package com.alessandrosisto.countryfinder.ui.screens.home

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.models.Continent
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.Language
import com.alessandrosisto.countryfinder.repo.CountryRepositoryInterface
import com.alessandrosisto.countryfinder.utilis.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val countryRepository: CountryRepositoryInterface
) : ViewModel() {

    private var cachedCountriesFeed: List<Country> = listOf()

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val uiState = _homeUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _homeUiState.value
    )

    private var _scrollToTop = MutableStateFlow(false)
    val scrollToTop: StateFlow<Boolean>
        get() = _scrollToTop

    init {
        setupContinentsAndLanguages()
    }

    private fun setupContinentsAndLanguages() {
        _homeUiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            try {
                val deferredContinents = async { countryRepository.getAllContinents() }
                val deferredLanguages = async { countryRepository.getAllLanguages() }
                when (val result = deferredContinents.await()) {
                    is Result.Success -> {
                        log("allContinents : ${result.data.printList()}", "MAIN_FLOW")
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                allContinents = result.data.map { it.toEntryDialog() },
                                isLoading = false
                            )
                        }
                        selectContinent(result.data.first())
                    }
                    is Result.Error -> {
                        updateErrorMessages(R.string.generic_error)
                    }
                }
                when (val result = deferredLanguages.await()) {
                    is Result.Success -> {
                        log("allLanguages : ${result.data.printList()}", "MAIN_FLOW")
                        _homeUiState.update {
                            it.copy(
                                allLanguages = result.data.createLanguagesEntry(),
                                isLoading = false
                            )
                        }
                    }
                    is Result.Error -> {
                        updateErrorMessages(R.string.generic_error)
                    }
                }
            } catch (e: Exception) {
                log(e.message ?: "", "ERROR init HomeViewModel", TLog.E)
                updateErrorMessages(R.string.generic_error)
                e.stackTraceToString()
            }
        }
    }

    fun selectContinent(continent: Continent) {
        _homeUiState.update { it.copy(selectedContinent = continent.toEntryDialog()) }
        refreshCountries(continent.code)
    }

    fun selectLanguage(language: Language) {
        _homeUiState.update {
            it.copy(
                countriesFeed = cachedCountriesFeed.filterLanguage(language.code),
                selectedLanguage = language.toEntryDialog()
            )
        }
    }

    /**
     * Refresh countries and update the UI state accordingly
     */
    private fun refreshCountries(codeContinent: String) {
        _homeUiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            try {
                when (val result = countryRepository.getAllCountriesInContinent(codeContinent)) {
                    is Result.Success -> {
                        log("refreshCountries : ${result.data.printList()}", "MAIN_FLOW")
                        _homeUiState.update {
                            cachedCountriesFeed = result.data
                            it.copy(
                                countriesFeed = cachedCountriesFeed.filterLanguage(it.selectedLanguage.code),
                                isLoading = false
                            )
                        }
                        updateScrollToTop(true)
                    }
                    is Result.Error -> {
                        updateErrorMessages(R.string.generic_error)
                    }
                }
            } catch (e: Exception) {
                log(e.message ?: "", "ERROR refreshCountries", TLog.E)
                updateErrorMessages(R.string.generic_error)
                e.stackTraceToString()
            }
        }
    }

    fun updateScrollToTop(scroll: Boolean) {
        viewModelScope.launch(dispatcher) {
            _scrollToTop.emit(scroll)
        }
    }

    private fun updateErrorMessages(@StringRes stringRes: Int) {
        _homeUiState.update {
            val errorMessages = it.errorMessages + ErrorMessage(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = stringRes
            )
            it.copy(
                errorMessages = errorMessages,
                isLoading = false
            )
        }
    }

    fun handleConnectionAction(isOnline: Boolean, action: (Type) -> Unit): (Type) -> Unit {
        return if (isOnline) {
            if (_homeUiState.value.allContinents.isEmpty()) {
                { setupContinentsAndLanguages() }
            } else {
                action
            }
        } else {
            { updateErrorMessages(R.string.connection_less_error) }
        }
    }

    /**
     * Notify that an error was displayed on the screen ed remove from state [errorMessages]
     */
    fun errorShown(errorId: Long) {
        _homeUiState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    /**
     * Factory for HomeViewModel that takes [CountryRepositoryInterface] as a dependency
     */
    companion object {
        fun provideFactory(
            dispatcher: CoroutineDispatcher = Dispatchers.Main,
            countryRepository: CountryRepositoryInterface,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(dispatcher, countryRepository) as T
            }
        }
    }
}