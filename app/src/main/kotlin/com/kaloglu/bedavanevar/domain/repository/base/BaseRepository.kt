package com.kaloglu.bedavanevar.domain.repository.base

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.repository.interfaces.Repository

abstract class BaseRepository : Repository {
    abstract val collectionRef: CollectionReference

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

    override fun <M : BaseModel> add(model: M): Task<Void> {
        val newDocument: DocumentReference
        when {
            model.id.isNotEmpty() -> {
                newDocument = collectionRef.document(model.id)
            }
            else -> {
                newDocument = collectionRef.document()
                model.id = newDocument.id
            }
        }

        return newDocument.set(model)
    }

    override fun remove(id: String) =
            collectionRef.document(id).delete()

    protected open fun applyLimit(query: Query): Query {
        var queryT = query
        queryT = query.limit(getLimit())
        return queryT
    }

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
