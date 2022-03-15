package com.alessandrosisto.countryfinder.models

data class Continent(
    override val code: String,
    override val name: String,
    val countries: List<Country>? = null
) : IdentifyInterface
