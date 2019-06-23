package com.kaloglu.bedavanevar.presentation.raffle

import androidx.lifecycle.MediatorLiveData
import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.Attendee
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
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

    override fun enrollRaffle(raffle: Raffle, onError: (Exception) -> Unit, onSuccess: () -> Unit) {
        genericDependencies?.userRepository
                ?.createEnrollRecord(
                        raffle.id,
                        onSuccess = { enrollDate ->
                            repository.addAttendUser(
                                    Attendee(activeUser?.id, enrollDate),
                                    onSuccess,
                                    onError
                            )
                        },
                        onError = onError
                )

    }

}
