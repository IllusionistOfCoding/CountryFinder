package com.alessandrosisto.countryfinder.repo

import com.alessandrosisto.countryfinder.utilis.Result
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment

/**
 * Interface to the Country data layer.
 */
interface CountryRepositoryInterface {

    suspend fun getAllContinents(): Result<List<ContinentFragment>>

    suspend fun getAllLanguages(): Result<List<LanguageFragment>>

    suspend fun getCountry(code: String): Result<CountryFragment>

    suspend fun getAllCountriesInContinent(code: String): Result<List<CountryFragment>>

}