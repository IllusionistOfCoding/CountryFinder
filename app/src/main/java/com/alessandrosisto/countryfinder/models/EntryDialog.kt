package com.alessandrosisto.countryfinder.models

import com.alessandrosisto.countryfinder.utilis.CONTINENT_TYPE

data class EntryDialog(
    val type: String = CONTINENT_TYPE,
    val code: String,
    val name: String? = null
)