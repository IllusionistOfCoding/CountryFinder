package com.alessandrosisto.countryfinder

import android.app.Application
import com.alessandrosisto.countryfinder.repo.AppContainer
import com.alessandrosisto.countryfinder.repo.AppContainerImpl


class ApolloTestApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}