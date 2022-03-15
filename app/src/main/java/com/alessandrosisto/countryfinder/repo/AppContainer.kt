package com.alessandrosisto.countryfinder.repo

import android.content.Context

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val context : Context
    val countryRepository: CountryRepositoryInterface
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val context : Context = applicationContext

    override val countryRepository: CountryRepositoryInterface by lazy {
        CountryRepository()
    }

}