package com.alessandrosisto.countryfinder.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import com.alessandrosisto.countryfinder.ApolloTestApplication
import com.alessandrosisto.countryfinder.utilis.TLog
import com.alessandrosisto.countryfinder.utilis.log

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as ApolloTestApplication).container
        setContent {
            CountryFinderApp(appContainer)
        }
    }
}