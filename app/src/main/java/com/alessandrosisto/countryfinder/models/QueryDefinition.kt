package com.alessandrosisto.countryfinder.models

import GetContinentQuery
import GetContinentsQuery
import GetCountryQuery
import GetLanguagesQuery
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query

interface IQueryDefinition<T> {
    val query: Query<out Operation.Data, out Operation.Data, *>
    val rootKey: String?
        get() = null
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