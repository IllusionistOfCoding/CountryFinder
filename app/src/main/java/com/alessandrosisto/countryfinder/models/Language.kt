package com.alessandrosisto.countryfinder.models

import com.alessandrosisto.countryfinder.models.IdentifyInterface

data class Language(
    override val code: String,
    override val name: String,
    val native: String? = null,
    val rtl: Boolean = false
) : IdentifyInterface