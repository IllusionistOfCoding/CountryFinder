package com.alessandrosisto.countryfinder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountryFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}