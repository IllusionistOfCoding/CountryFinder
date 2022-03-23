package com.alessandrosisto.countryfinder.di

import com.alessandrosisto.countryfinder.repo.CountryRepository
import com.alessandrosisto.countryfinder.repo.ICountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryRepository(
        repository: CountryRepository
    ): ICountryRepository
}