package com.kaloglu.bedavanevar.domain.repository.interfaces

import com.google.firebase.firestore.Query
import com.kaloglu.bedavanevar.domain.livedata.QueryLiveData
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface ListRepository : Repository {

    fun <M : BaseModel> get(filters: Filters? = null): QueryLiveData<M>

    fun toQuery(filters: Filters?): Query

    fun <M : BaseModel> add(
            model: M,
            onComplete: (Exception?) -> Unit = {_: Exception? -> }
    )

    fun remove(
            id: String,
            onComplete: (String, Exception?) -> Unit = { _: String, _: Exception? -> }
    )

}
