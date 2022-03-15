package com.alessandrosisto.countryfinder.repo.fake

import com.alessandrosisto.countryfinder.models.Continent
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.Language
import com.alessandrosisto.countryfinder.repo.CountryRepositoryInterface
import com.alessandrosisto.countryfinder.utilis.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Implementation of [CountryRepositoryInterface] that returns a hardcoded list of
 * countries, continents and languages with resources after some delay in a background thread.
 */
class FakeCountryRepository: CountryRepositoryInterface {

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    override suspend fun getAllContinents(): Result<List<Continent>> {
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

    override suspend fun getAllLanguages(): Result<List<Language>> {
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

    override suspend fun getCountry(code: String): Result<Country> {
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

    override suspend fun getAllCountriesInContinent(code: String): Result<List<Country>> {
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