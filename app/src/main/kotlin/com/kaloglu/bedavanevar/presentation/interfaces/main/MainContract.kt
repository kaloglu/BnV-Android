package com.kaloglu.bedavanevar.presentation.interfaces.main

import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpView

interface MainContract {

    interface View : MvpView {
        fun showContentContainer(show: Boolean = true)
    }

    interface Presenter : MvpPresenter<View>, FragmentNavigator.FragmentCallback {
        fun newRaffle()
    }
}
