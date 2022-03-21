package com.alessandrosisto.countryfinder.utilis

import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.models.EntryDialog
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment


fun CountryFragment.additionalInfo(): Int {
    return when (this.continent.code) {
        "AF" -> R.drawable.africa
        "AN" -> R.drawable.antarctica
        "AS" -> R.drawable.asia
        "EU" -> R.drawable.europe
        "NA" -> R.drawable.north_america
        "SA" -> R.drawable.south_america
        "OC" -> R.drawable.oceania
        else -> throw IllegalAccessException()
    }
}

fun List<String>.printList(): String {
    return this.reduce { acc, s -> "$acc$s, " }
}

fun List<LanguageFragment>.createLanguagesEntry(): List<EntryDialog> {
    return listOf(
        EntryDialog(
            type = Type.Language,
            code = NONE_CODE
        )
    ) + this.map { it.toEntryDialog() }
}

fun ContinentFragment.toEntryDialog(): EntryDialog {
    return EntryDialog(
        type = Type.Continent,
        code = this.code,
        name = this.name
    )
}

fun LanguageFragment.toEntryDialog(): EntryDialog {
    return EntryDialog(
        type = Type.Language,
        code = this.code,
        name = this.name
    )
}

fun EntryDialog.toContinent(): ContinentFragment {
    return ContinentFragment(
        code = this.code,
        name = this.name ?: "none",
        countries = listOf()
    )
}

fun EntryDialog.toLanguage(): LanguageFragment {
    return LanguageFragment(
        code = this.code,
        name = this.name ?: "none",
        native_ = "",
        rtl = false
    )
}

fun List<CountryFragment>.filterLanguage(code: String): List<CountryFragment> {
    if (code == NONE_CODE) {
        return this
    }
    return this.filter { country ->
        country.languages.forEach { lang ->
            if (lang.code == code) {
                return@filter true
            }
        }
        return@filter false
    }
}

fun Type.typeToDisplayTitle(): String {
    return when (this) {
        Type.Continent -> "Continents"
        Type.Country -> "Countries"
        else -> "Languages"
    }
}