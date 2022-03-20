package com.alessandrosisto.countryfinder.utilis

import com.alessandrosisto.countryfinder.R
import com.alessandrosisto.countryfinder.models.*

fun Country.additionalInfo(): Int {
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

fun List<IdentifyInterface>.printList(): String {
    return this.map { it.name }.reduce { acc, s -> "$acc$s, " }
}

fun List<IdentifyInterface>.createLanguagesEntry(): List<EntryDialog> {
    return listOf(
        EntryDialog(
            type = Type.Language,
            code = NONE_CODE
        )
    ) + this.map { it.toEntryDialog() }
}

fun IdentifyInterface.toEntryDialog(): EntryDialog {
    val type = when (this) {
        is Continent -> Type.Continent
        is Language -> Type.Language
        else -> Type.Country
    }
    return EntryDialog(
        type = type,
        code = this.code,
        name = this.name
    )
}

fun EntryDialog.toContinent(): Continent {
    return Continent(
        code = this.code,
        name = this.name ?: "none"
    )
}

fun EntryDialog.toLanguage(): Language {
    return Language(
        code = this.code,
        name = this.name ?: "none"
    )
}

fun List<Country>.filterLanguage(code: String): List<Country> {
    if (code == NONE_CODE) { return this }
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