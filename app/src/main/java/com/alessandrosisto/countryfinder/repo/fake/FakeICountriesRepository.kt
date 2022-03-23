package com.alessandrosisto.countryfinder.repo.fake

import com.alessandrosisto.countryfinder.repo.ICountryRepository
import com.alessandrosisto.countryfinder.utilis.Result
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Implementation of [ICountryRepository] that returns a hardcoded list of
 * countries, continents and languages with resources after some delay in a background thread.
 */
class FakeCountryRepository: ICountryRepository {

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    override suspend fun getAllContinents(): Result<List<ContinentFragment>> {
        return withContext(Dispatchers.IO) {
            val response = fakeAllContinents
            delay(500) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(response)
            }
        }
    }

    override suspend fun getAllLanguages(): Result<List<LanguageFragment>> {
        return withContext(Dispatchers.IO) {
            val response = fakeAllLanguages
            delay(500) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(response)
            }
        }
    }

    override suspend fun getCountry(code: String): Result<CountryFragment> {
        return withContext(Dispatchers.IO) {
            val response = fakeCountry
            delay(500) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(response)
            }
        }
    }

    override suspend fun getAllCountriesInContinent(code: String): Result<List<CountryFragment>> {
        return withContext(Dispatchers.IO) {
            val response = fakeAllCountriesInNA
            delay(500) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(response)
            }
        }
    }

    /**
     * Randomly fail some loads to simulate a real network.
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0

}