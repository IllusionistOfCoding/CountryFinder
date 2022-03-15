package com.alessandrosisto.countryfinder.repo

import com.alessandrosisto.countryfinder.models.Continent
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.Language
import com.alessandrosisto.countryfinder.utilis.Result

/**
 * Interface to the Country data layer.
 */
interface CountryRepositoryInterface {

    suspend fun getAllContinents(): Result<List<Continent>>

    suspend fun getAllLanguages(): Result<List<Language>>

    suspend fun getCountry(code: String): Result<Country>

    suspend fun getAllCountriesInContinent(code: String): Result<List<Country>>

}