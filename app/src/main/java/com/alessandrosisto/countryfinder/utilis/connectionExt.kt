package com.alessandrosisto.countryfinder.utilis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


fun Context.isOnline(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    log("NetworkCapabilities.TRANSPORT_CELLULAR", "Internet", TLog.I)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    log("NetworkCapabilities.TRANSPORT_WIFI", "Internet", TLog.I)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    log("NetworkCapabilities.TRANSPORT_ETHERNET", "Internet", TLog.I)
                    return true
                }
            }
        }
        return false
    } else {
        val connectivityManager = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetwork = connectivityManager.activeNetworkInfo
        return if (activeNetwork != null) {
            // connected to the internet
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> true
            }
        } else {
            // not connected to the internet
            false
        }
    }
}