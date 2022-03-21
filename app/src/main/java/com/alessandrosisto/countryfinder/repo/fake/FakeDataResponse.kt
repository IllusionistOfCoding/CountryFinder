package com.alessandrosisto.countryfinder.repo.fake

import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.utilis.Type
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment

val fakeAllContinents = listOf(
    ContinentFragment(code = "AF", name = "Africa", countries = listOf()),
    ContinentFragment(code = "AN", name = "Antarctica", countries = listOf()),
    ContinentFragment(code = "AS", name = "Asia", countries = listOf()),
    ContinentFragment(code = "EU", name = "Europe", countries = listOf()),
    ContinentFragment(code = "NA", name = "North America", countries = listOf()),
    ContinentFragment(code = "OC", name = "Oceania", countries = listOf()),
    ContinentFragment(code = "SA", name = "South America", countries = listOf()),
)

val fakeContinent = ContinentFragment(code = "EU", name = "Europe", countries = listOf())

val fakeCountry = CountryFragment(
    code = "AD",
    name = "Andorra",
    emoji = "\uD83C\uDDE6\uD83C\uDDE9",
    continent = CountryFragment.Continent(
        code = "EU",
        name = "EUROPE"
    ),
    languages = listOf(
        CountryFragment.Language(
            code = "ca",
            name = "Catalan",
            native_ = "",
        )
    ),
    native_ = "Andorran",
    phone = "376",
    capital = "Andorra la Vella",
    currency = "EUR",
)

val emptyCountry = CountryFragment(
    code = "AA",
    name = "Country Name",
    emoji = "",
    continent = CountryFragment.Continent(
        code = "",
        name = "",
    ),
    languages = listOf(
        CountryFragment.Language(
            code = "",
            name = "",
            native_ = "",
        )
    ),
    native_ = "",
    phone = "",
    capital = "",
    currency = "",
)

val fakeDetailCountry = CountryFragment(
    code = "AD",
    name = "Andorra",
    emoji = "\uD83C\uDDE6\uD83C\uDDE9",
    continent = CountryFragment.Continent(
        code = "EU",
        name = "Europe"
    ),
    languages = listOf(
        CountryFragment.Language(
            code = "ca",
            name = "Catalan",
            native_ = "",
        )
    ),
    native_ = "Andorran",
    phone = "376",
    capital = "Andorra la Vella",
    currency = "EUR",
)

val fakeAllCountries = listOf(
    fakeCountry,
    fakeCountry,
    fakeCountry,
    CountryFragment(
        code = "AE",
        name = "United Arab Emirates",
        emoji = "\uD83C\uDDE6\uD83C\uDDEA",
        continent = CountryFragment.Continent(
            code = "AS",
            name = "Asia"
        ),
        languages = listOf(
            CountryFragment.Language(
                code = "ar",
                name = "Arabic",
                native_ = "",
            )
        ),
        native_ = "Andorran",
        phone = "376",
        capital = "Andorra la Vella",
        currency = "EUR",
    ),
)

val fakeEntryDialog = listOf(
    EntryDialog(type = Type.Language, code = "af", name = "Afrikaans"),
    EntryDialog(type = Type.Language, code = "am", name = "Amharic"),
    EntryDialog(type = Type.Language, code = "ar", name = "Arabic"),
    EntryDialog(type = Type.Language, code = "ay", name = "Aymara"),
    EntryDialog(type = Type.Language, code = "az", name = "Azerbaijani"),
    EntryDialog(type = Type.Language, code = "be", name = "Belarusian"),
    EntryDialog(type = Type.Language, code = "bg", name = "Bulgarian"),
    EntryDialog(type = Type.Language, code = "bi", name = "Bislama"),
    EntryDialog(type = Type.Language, code = "bn", name = "Bengali"),
    EntryDialog(type = Type.Language, code = "bs", name = "Bosnian"),
    EntryDialog(type = Type.Language, code = "ca", name = "Catalan"),
    EntryDialog(type = Type.Language, code = "ch", name = "Chamorro"),
    EntryDialog(type = Type.Language, code = "cs", name = "Czech"),
    EntryDialog(type = Type.Language, code = "da", name = "Danish"),
    EntryDialog(type = Type.Language, code = "de", name = "German"),
)

val fakeAllLanguages = listOf(
    LanguageFragment(code = "af", name = "Afrikaans", native_ = "", rtl = false),
    LanguageFragment(code = "am", name = "Amharic", native_ = "", rtl = false),
    LanguageFragment(code = "ar", name = "Arabic", native_ = "", rtl = false),
    LanguageFragment(code = "ay", name = "Aymara", native_ = "", rtl = false),
    LanguageFragment(code = "az", name = "Azerbaijani", native_ = "", rtl = false),
    LanguageFragment(code = "be", name = "Belarusian", native_ = "", rtl = false),
    LanguageFragment(code = "bg", name = "Bulgarian", native_ = "", rtl = false),
    LanguageFragment(code = "bi", name = "Bislama", native_ = "", rtl = false),
    LanguageFragment(code = "bn", name = "Bengali", native_ = "", rtl = false),
    LanguageFragment(code = "bs", name = "Bosnian", native_ = "", rtl = false),
    LanguageFragment(code = "ca", name = "Catalan", native_ = "", rtl = false),
    LanguageFragment(code = "ch", name = "Chamorro", native_ = "", rtl = false),
    LanguageFragment(code = "cs", name = "Czech", native_ = "", rtl = false),
    LanguageFragment(code = "da", name = "Danish", native_ = "", rtl = false),
    LanguageFragment(code = "de", name = "German", native_ = "", rtl = false),
)

val fakeLanguage = fakeAllLanguages.first()

val fakeAllCountriesInNA = listOf(
    CountryFragment(
        code = "AG",
        name = "Antigua and Barbuda",
        emoji = "\uD83C\uDDE6\uD83C\uDDEC",
        continent = CountryFragment.Continent(
            code = "NA",
            name = "Asia"
        ),
        languages = listOf(
            CountryFragment.Language(
                code = "en",
                name = "English",
                native_ = ""
            )
        ),
        native_ = "Andorran",
        phone = "376",
        capital = "Andorra la Vella",
        currency = "EUR",
    ),
    CountryFragment(
        code = "AI",
        name = "Anguilla",
        emoji = "\uD83C\uDDE6\uD83C\uDDEE",
        continent = CountryFragment.Continent(
            code = "NA",
            name = "Asia"
        ),
        languages = listOf(
            CountryFragment.Language(
                code = "en",
                name = "English",
                native_ = ""
            )
        ),
        native_ = "Andorran",
        phone = "376",
        capital = "Andorra la Vella",
        currency = "EUR",
    ),
)