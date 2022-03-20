package com.alessandrosisto.countryfinder.utilis

import java.io.Serializable

const val NONE_CODE = "00"

const val TEST_progress_bar = "TEST_progress_bar"
const val TEST_button_filter = "TEST_button_filter"
const val TEST_emoji_flag = "TEST_emoji_flag"
const val TEST_card_country = "TEST_card_country"

enum class Type : Serializable {
    Country, Continent, Language
}

