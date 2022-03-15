package com.alessandrosisto.countryfinder.utilis

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.alessandrosisto.countryfinder.BuildConfig

fun Any.log(msg: String, tag: String = this::class.java.simpleName, type: TLog = TLog.D) {
    if (BuildConfig.DEBUG) {
        when (type) {
            TLog.I -> Log.i(tag, msg)
            TLog.W -> Log.w(tag, msg)
            TLog.E -> Log.e(tag, msg)
            else -> Log.d(tag, msg)
        }
    }
}

enum class TLog {
    I, D, W, E
}

fun toastDebug(ctx: Context?, msg: String?) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(
            ctx,
            msg ?: "",
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun Exception.printError() {
    if (BuildConfig.DEBUG) {
        this.printStackTrace()
    }
}