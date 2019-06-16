package com.kaloglu.bedavanevar.data.repository.raffle

import com.google.firebase.firestore.CollectionReference
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.repository.base.BaseListRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class RaffleListRepository @Inject constructor(
        @Named(TableNames.RAFFLE_LIST)
        override val collectionRef: CollectionReference
) : BaseListRepository() {

    override fun getModelClass() = Raffle::class.java

    @Suppress("UNCHECKED_CAST")
    override fun <M : BaseModel> get(filters: Filters?) =
            QueryLiveData(toQuery(filters), getModelClass() as Class<M>)


}
