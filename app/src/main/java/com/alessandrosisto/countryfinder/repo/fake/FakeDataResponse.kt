package com.alessandrosisto.countryfinder.repo.fake

import com.alessandrosisto.countryfinder.models.Continent
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.models.Language

val fakeAllContinents = listOf(
    Continent(code = "AF", name = "Africa"),
    Continent(code = "AN", name = "Antarctica"),
    Continent(code = "AS", name = "Asia"),
    Continent(code = "EU", name = "Europe"),
    Continent(code = "NA", name = "North America"),
    Continent(code = "OC", name = "Oceania"),
    Continent(code = "SA", name = "South America"),
)

val fakeContinent = Continent(code = "EU", name = "Europe")

val fakeCountry = Country(
    code = "AD",
    name = "Andorra",
    emoji = "\uD83C\uDDE6\uD83C\uDDE9",
    continent = Continent(
        code = "EU",
        name = "Europe"
    ),
    languages = listOf(
        Language(
            code = "ca",
            name = "Catalan",
            native = "Català"
        )
    )
)

val emptyCountry = Country(
    code = "AA",
    name = "something went wrong check the connection",
    emoji = "",
    continent = Continent(
        code = "",
        name = ""
    ),
    languages = listOf(
        Language(
            code = "",
            name = "",
            native = ""
        )
    ),
)

val fakeDetailCountry = Country(
    code = "AD",
    name = "Andorra",
    emoji = "\uD83C\uDDE6\uD83C\uDDE9",
    continent = Continent(
        code = "EU",
        name = "Europe"
    ),
    languages = listOf(
        Language(
            code = "ca",
            name = "Catalan",
            native = "Català"
        )
    ),
    native = "Andorran",
    phone = "376",
    capital = "Andorra la Vella",
    currency = "EUR",
    states = emptyList()
)

val fakeAllCountries = listOf(
    fakeCountry,
    Country(
        code = "AE",
        name = "United Arab Emirates",
        emoji = "\uD83C\uDDE6\uD83C\uDDEA",
        continent = Continent(
            code = "AS",
            name = "Asia"
        ),
        languages = listOf(
            Language(
                code = "ar",
                name = "Arabic",
                native = "العربية"
            )
        )
    ),
    Country(
        code = "AF",
        name = "Afghanistan",
        emoji = "\uD83C\uDDE6\uD83C\uDDEB",
        continent = Continent(
            code = "AS",
            name = "Asia"
        ),
        languages = listOf(
            Language(
                code = "ps",
                name = "Pashto",
                native = "پښتو"
            ),
            Language(
                code = "uz",
                name = "Uzbek",
                native = "Ўзбек"
            ),
            Language(
                code = "tk",
                name = "Turkmen",
                native = "Туркмен / تركمن"
            )
        )
    ),
    Country(
        code = "AG",
        name = "Antigua and Barbuda",
        emoji = "\uD83C\uDDE6\uD83C\uDDEC",
        continent = Continent(
            code = "NA",
            name = "North America"
        ),
        languages = listOf(
            Language(
                code = "en",
                name = "English",
                native = "English"
            )
        )
    ),
    Country(
        code = "AI",
        name = "Anguilla",
        emoji = "\uD83C\uDDE6\uD83C\uDDEE",
        continent = Continent(
            code = "NA",
            name = "North America"
        ),
        languages = listOf(
            Language(
                code = "en",
                name = "English",
                native = "English"
            )
        )
    ),
)

val fakeEntryDialog = listOf(
    EntryDialog(type = "language", code = "af", name = "Afrikaans"),
    EntryDialog(type = "language",code = "am", name = "Amharic"),
    EntryDialog(type = "language",code = "ar", name = "Arabic"),
    EntryDialog(type = "language",code = "ay", name = "Aymara"),
    EntryDialog(type = "language",code = "az", name = "Azerbaijani"),
    EntryDialog(type = "language",code = "be", name = "Belarusian"),
    EntryDialog(type = "language",code = "bg", name = "Bulgarian"),
    EntryDialog(type = "language",code = "bi", name = "Bislama"),
    EntryDialog(type = "language",code = "bn", name = "Bengali"),
    EntryDialog(type = "language",code = "bs", name = "Bosnian"),
    EntryDialog(type = "language",code = "ca", name = "Catalan"),
    EntryDialog(type = "language",code = "ch", name = "Chamorro"),
    EntryDialog(type = "language",code = "cs", name = "Czech"),
    EntryDialog(type = "language",code = "da", name = "Danish"),
    EntryDialog(type = "language",code = "de", name = "German"),
)

val fakeAllLanguages = listOf(
    Language(code = "af", name = "Afrikaans"),
    Language(code = "am", name = "Amharic"),
    Language(code = "ar", name = "Arabic"),
    Language(code = "ay", name = "Aymara"),
    Language(code = "az", name = "Azerbaijani"),
    Language(code = "be", name = "Belarusian"),
    Language(code = "bg", name = "Bulgarian"),
    Language(code = "bi", name = "Bislama"),
    Language(code = "bn", name = "Bengali"),
    Language(code = "bs", name = "Bosnian"),
    Language(code = "ca", name = "Catalan"),
    Language(code = "ch", name = "Chamorro"),
    Language(code = "cs", name = "Czech"),
    Language(code = "da", name = "Danish"),
    Language(code = "de", name = "German"),
)

val fakeLanguage = Language(code = "af", name = "Afrikaans")

val fakeAllCountriesInNA = listOf(
    Country(
        code = "AG",
        name = "Antigua and Barbuda",
        emoji = "\uD83C\uDDE6\uD83C\uDDEC",
        continent = Continent(
            code = "NA",
            name = "North America"
        ),
        languages = listOf(
            Language(
                code = "en",
                name = "English",
                native = "English"
            )
        )
    ),
    Country(
        code = "AI",
        name = "Anguilla",
        emoji = "\uD83C\uDDE6\uD83C\uDDEE",
        continent = Continent(
            code = "NA",
            name = "North America"
        ),
        languages = listOf(
            Language(
                code = "en",
                name = "English",
                native = "English"
            )
        )
    ),
)