package com.kaloglu.bedavanevar.domain.repository.interfaces

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.FireStoreLiveList

interface Repository<M : BaseModel> {

    fun getModelClass(): Class<M>
    fun get(filters: Filters?): FireStoreLiveList<M>
    fun toQuery(filters: Filters?): Query
    fun add(model: M): Task<Void>
    fun remove(id: String): Task<Void>

}
