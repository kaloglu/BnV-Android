package com.kaloglu.bedavanevar.domain.repository.base

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.livedata.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.repository.interfaces.ListRepository
import com.kaloglu.bedavanevar.utils.extensions.addToCollection
import com.kaloglu.bedavanevar.utils.extensions.invoke

abstract class BaseListRepository : ListRepository {

    override lateinit var documentRef: DocumentReference

    @Suppress("UNCHECKED_CAST")
    override fun <M : BaseModel> get(filters: Filters?) =
            QueryLiveData(toQuery(filters), getModelClass() as Class<M>)

    override fun toQuery(filters: Filters?): Query {
        var query: Query = collectionRef
        query = applyOrderBy(query, filters)

        query = applyFilter(query, filters)

        query = applyLimit(query)

        return query
    }

    override fun <M : BaseModel> add(model: M, onComplete: (M, Exception?) -> Unit) =
            collectionRef.addToCollection(model, onComplete)

    override fun remove(id: String, onComplete: (String, Exception?) -> Unit) =
            collectionRef.document(id).delete()(id, onComplete)

    protected open fun applyLimit(query: Query) = query.limit(getLimit())

    protected open fun applyOrderBy(query: Query, filters: Filters?): Query {
        var queryT = query
        filters?.run {
            sortMap.entries.forEach {
                queryT = query.orderBy(it.key, it.value)
            }
        }
        return queryT
    }

    protected open fun applyFilter(query: Query, filters: Filters?): Query {
        var queryT: Query = query
        filters?.run {
            equalToMap.entries.forEach {
                queryT = query.whereEqualTo(it.key, it.value)
            }
        }

        return queryT
    }

    protected open fun getLimit(): Long = DEFAULT_QUERY_LIMIT

    protected open fun getOrderFieldPath(): FieldPath? = null

    companion object {
        const val DEFAULT_QUERY_LIMIT: Long = 20
    }

}
