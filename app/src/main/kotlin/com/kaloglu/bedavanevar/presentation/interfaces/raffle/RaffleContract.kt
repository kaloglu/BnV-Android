package com.kaloglu.bedavanevar.presentation.interfaces.raffle

import com.kaloglu.bedavanevar.domain.livedata.CountLiveData
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.ResponseLiveView

interface RaffleContract {

    interface View : ResponseLiveView {
        var model: Raffle

        fun getRaffleName(): String
    }

    interface Presenter : MvpPresenter<View> {
        fun enrollRaffle(raffle: Raffle)
        fun getData(id: String): DocumentLiveData<Raffle>
        fun getAttendCount(): CountLiveData
    }

    interface ListView : MvpListView

    interface ListPresenter : MvpListPresenter<ListView> {

        fun createRaffle()
    }

}
