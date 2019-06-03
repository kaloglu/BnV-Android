package com.kaloglu.bedavanevar.domain.repository.interfaces

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface Repository {

    fun getModelClass(): Class<*>
    fun <M : BaseModel> get(filters: Filters?): QueryLiveData<M>
    fun toQuery(filters: Filters?): Query
    fun <M : BaseModel> add(model: M): Task<Void>
    fun remove(id: String): Task<Void>

}
