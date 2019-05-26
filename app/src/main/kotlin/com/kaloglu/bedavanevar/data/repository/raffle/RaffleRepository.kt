package com.kaloglu.bedavanevar.data.repository.raffle

import com.google.firebase.firestore.CollectionReference
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class RaffleRepository @Inject constructor(
        @Named(TableNames.RAFFLE_LIST)
        override val collectionRef: CollectionReference
) : BaseRepository() {

    override fun getModelClass() = Raffle::class.java

}
