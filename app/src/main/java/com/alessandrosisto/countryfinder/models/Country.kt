package com.alessandrosisto.countryfinder.models

data class Country(
    override val code: String,
    override val name: String,
    val emoji: String,
    val languages: List<Language>,
    val continent: Continent,
    val capital: String? = null,
    val currency: String? = null,
    val emojiU: String? = null,
    val native: String? = null,
    val phone: String? = null,
    val states: List<State>? = null
) : IdentifyInterface

data class State(
    val code: String,
    val name: String,
)