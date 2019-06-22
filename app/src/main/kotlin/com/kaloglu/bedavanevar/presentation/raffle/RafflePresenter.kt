package com.kaloglu.bedavanevar.presentation.raffle

import androidx.lifecycle.MediatorLiveData
import com.google.firebase.Timestamp
import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.Attendee
import com.kaloglu.bedavanevar.domain.model.Enroll
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.addToCollection
import com.kaloglu.bedavanevar.viewobjects.CalculatedResource
import javax.inject.Inject

class RafflePresenter @Inject constructor(
        override val repository: RaffleRepository,
        override val genericDependencies: GenericDependencies?
) : BasePresenter<RaffleContract.View>(), RaffleContract.Presenter {

    override fun getData(id: String): DocumentLiveData<Raffle> = repository.get(id)

    override fun getAttendanceInfo(): MediatorLiveData<CalculatedResource?> =
            genericDependencies?.userRepository?.getAttendanceInfo(repository.documentRef.id)
                    ?: MediatorLiveData()

    override fun enrollRaffle(raffle: Raffle) {
        val attendee = Attendee(activeUser?.id)
        attendee.attendDate = Timestamp.now()

        repository.documentRef
                .addToCollection(TableNames.ATTENDEES, attendee) { _: Attendee, attendError: Exception? ->
                    attendError?.run {
                        getView()?.showSnackbar(localizedMessage)
                        return@addToCollection
                    }

                    genericDependencies?.userRepository?.documentRef
                            ?.addToCollection(TableNames.ENROLLS, enroll()) { _: Enroll, enrollError: Exception? ->
                                enrollError?.run {
                                    getView()?.showSnackbar(localizedMessage)
                                }
                            }

                    getView()?.showSnackbar("Tebrikler çekilişe katıldınız!")
                }


    }

    private fun enroll() = Enroll(raffleId = repository.documentRef.id, enrollDate = Timestamp.now())

}
