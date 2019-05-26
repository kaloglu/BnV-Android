package com.kaloglu.bedavanevar.presentation.interfaces.raffle

import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView

interface RaffleContract {

    interface View : FormContract.FormView {
        fun getRaffleName(): String
    }

    interface Presenter : FormContract.FormPresenter<View>

    interface ListView : MvpListView

    interface ListPresenter : MvpListPresenter<ListView> {

        fun createRaffle()
    }

}
