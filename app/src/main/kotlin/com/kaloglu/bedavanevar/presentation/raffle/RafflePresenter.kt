package com.kaloglu.bedavanevar.presentation.raffle

import com.google.firebase.Timestamp
import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.domain.CountLiveData
import com.kaloglu.bedavanevar.domain.DocumentLiveData
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.Attendee
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.addToCollection
import javax.inject.Inject

class RafflePresenter @Inject constructor(
        override val repository: RaffleRepository,
        override val genericDependencies: GenericDependencies?
) : BasePresenter<RaffleContract.View>(), RaffleContract.Presenter {

    override fun getData(id: String): DocumentLiveData<Raffle> = repository.get(id)

    override fun getAttendCount(): CountLiveData {
        val query = repository.documentRef.collection(TableNames.ATTENDEE_LIST)

        return CountLiveData(query.whereEqualTo("userId", activeUser?.id))
    }

    override fun enrollRaffle(raffle: Raffle) {
        val attendee = Attendee(activeUser?.id)
        attendee.attendDate = Timestamp.now()

        repository.documentRef
                .addToCollection(TableNames.ATTENDEE_LIST, attendee) { _: Attendee, exception: Exception? ->
                    exception?.run {
                        getView()?.showSnackbar(localizedMessage)
                        return@addToCollection
                    }

                    getView()?.showSnackbar("Tebrikler çekilişe katıldınız!")
                }
    }

}
