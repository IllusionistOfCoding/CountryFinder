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
            CountryFinderApp(appContainer, ::isOnline)
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    log("NetworkCapabilities.TRANSPORT_CELLULAR", "Internet", TLog.I)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    log("NetworkCapabilities.TRANSPORT_WIFI","Internet", TLog.I)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    log("NetworkCapabilities.TRANSPORT_ETHERNET","Internet", TLog.I)
                    return true
                }
            }
        }
        return false
    }
}