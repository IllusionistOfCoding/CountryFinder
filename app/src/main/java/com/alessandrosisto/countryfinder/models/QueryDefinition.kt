package com.alessandrosisto.countryfinder.models

import GetContinentQuery
import GetContinentsQuery
import GetCountriesQuery
import GetCountryQuery
import GetLanguageQuery
import GetLanguagesQuery
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.google.gson.reflect.TypeToken
import fragment.ContinentFragment
import fragment.CountryFragment
import fragment.LanguageFragment
import java.lang.reflect.Type

interface IQueryDefinition<T> {
    val query: Query<out Operation.Data, out Operation.Data, *>
    val rootKey: String?
        get() = null
    val returnType: Type
        get() = object : TypeToken<T>() {}.rawType
}

object AllContinentsQuery : IQueryDefinition<GetContinentsQuery.Data> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetContinentsQuery()
}

class ContinentQuery(val id: String) : IQueryDefinition<GetContinentQuery.Data> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetContinentQuery(id)
}

class CountryQuery(val id: String) : IQueryDefinition<GetCountryQuery.Data> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetCountryQuery(id)
}

object AllLanguagesQuery : IQueryDefinition<GetLanguagesQuery.Data> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetLanguagesQuery()
}