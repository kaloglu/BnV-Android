package com.kaloglu.bedavanevar.data.repository.raffle

import com.google.firebase.firestore.CollectionReference
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.Attendee
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import com.kaloglu.bedavanevar.utils.extensions.addToCollection
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class RaffleRepository @Inject constructor(
        @Named(TableNames.RAFFLES)
        override val collectionRef: CollectionReference
) : BaseRepository() {

    override fun getModelClass() = Raffle::class.java

    fun addAttendUser(
            attendee: Attendee,
            onSuccess: () -> Unit,
            onError: (Exception) -> Unit
    ) {
        documentRef
                .addToCollection(TableNames.ATTENDEES, attendee) { attendError: Exception? ->
                    attendError?.run {
                        onError(this)
                        return@addToCollection
                    }

                    onSuccess()
                }

    }

}
