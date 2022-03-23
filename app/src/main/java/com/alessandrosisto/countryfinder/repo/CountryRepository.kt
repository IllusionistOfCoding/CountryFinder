package com.alessandrosisto.countryfinder.repo

import GetContinentQuery
import GetContinentsQuery
import GetCountryQuery
import GetLanguagesQuery
import com.alessandrosisto.countryfinder.models.*
import com.alessandrosisto.countryfinder.network.CountryFinderDataSource
import com.alessandrosisto.countryfinder.utilis.Result
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val dataSource: CountryFinderDataSource
) : ICountryRepository {


    override suspend fun getAllContinents(): Result<List<ContinentFragment>> {
        return try {
            val query = AllContinentsQuery
            val response: GetContinentsQuery.Data? = dataSource.fetchDataQuery(query)
            response?.continents?.map { it.fragments.continentFragment }?.let {
                Result.Success(it)
            } ?: run {
                Result.Success(emptyList())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAllLanguages(): Result<List<LanguageFragment>> {
        return try {
            val query = AllLanguagesQuery
            val response: GetLanguagesQuery.Data? = dataSource.fetchDataQuery(query)
            response?.languages?.map {
                it.fragments.languageFragment
            }?.let {
                Result.Success(it)
            } ?: run {
                Result.Success(emptyList())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCountry(code: String): Result<CountryFragment> {
        return try {
            val query = CountryQuery(code)
            val response: GetCountryQuery.Data? = dataSource.fetchDataQuery(query)
            response?.country?.fragments?.countryFragment?.let {
                Result.Success(it)
            } ?: run {
                Result.Error(IllegalStateException())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAllCountriesInContinent(code: String): Result<List<CountryFragment>> {
        return try {
            val query = ContinentQuery(code)
            val response: GetContinentQuery.Data? = dataSource.fetchDataQuery(query)
            response?.continent?.fragments?.continentFragment?.countries?.map {
                it.fragments.countryFragment
            }?.let {
                Result.Success(it)
            } ?: run {
                Result.Success(emptyList())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}