package com.kaloglu.bedavanevar.presentation.interfaces.raffle

import androidx.lifecycle.MediatorLiveData
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.ResponseLiveView
import com.kaloglu.bedavanevar.viewobjects.CalculatedResource

interface RaffleContract {

    interface View : ResponseLiveView {
        var model: Raffle

        fun getRaffleName(): String
    }

    interface Presenter : MvpPresenter<View> {
        fun enrollRaffle(
                raffle: Raffle,
                onError: (Exception) -> Unit = { getView()?.showSnackbar(it.localizedMessage) },
                onSuccess: () -> Unit = {}
        )
        fun getData(id: String): DocumentLiveData<Raffle>
        fun getAttendanceInfo(): MediatorLiveData<CalculatedResource?>
    }

    interface ListView : MvpListView

    interface ListPresenter : MvpListPresenter<ListView> {
        fun createRaffle()
    }

}
