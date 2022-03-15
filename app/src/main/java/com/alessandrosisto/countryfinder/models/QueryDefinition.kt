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
import java.lang.reflect.Type

interface IQueryDefinition<T> {
    val query: Query<out Operation.Data, out Operation.Data, *>
    val rootKey: String?
        get() = null
    val returnType: Type
        get() = object : TypeToken<T>() {}.rawType
}

object AllContinentsQuery : IQueryDefinition<List<Continent>> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetContinentsQuery()
    override val returnType: Type
        get() = object : TypeToken<List<Continent>>() {}.type
}

class ContinentQuery(val id: String) : IQueryDefinition<Continent> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetContinentQuery(id)
    override val returnType: Type
        get() = object : TypeToken<Continent>() {}.type
}

object AllCountriesQuery : IQueryDefinition<List<Country>> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetCountriesQuery()
    override val returnType: Type
        get() = object : TypeToken<List<Country>>() {}.type
}

class CountryQuery(val id: String) : IQueryDefinition<Country> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetCountryQuery(id)
    override val returnType: Type
        get() = object : TypeToken<Country>() {}.type
}

object AllLanguagesQuery : IQueryDefinition<List<Language>> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetLanguagesQuery()
    override val returnType: Type
        get() = object : TypeToken<List<Language>>() {}.type
}

class LanguageQuery(val id: String) : IQueryDefinition<Language> {
    override val query: Query<out Operation.Data, out Operation.Data, *>
        get() = GetLanguageQuery(id)
    override val returnType: Type
        get() = object : TypeToken<Language>() {}.type
}