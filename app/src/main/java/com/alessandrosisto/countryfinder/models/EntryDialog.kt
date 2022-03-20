package com.alessandrosisto.countryfinder.models

import com.alessandrosisto.countryfinder.utilis.Type

data class EntryDialog(
    val type: Type = Type.Continent,
    val code: String,
    val name: String? = null
)