package com.kaloglu.bedavanevar.presentation.interfaces.splash

import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpView

interface SplashContract {

    interface View : MvpView

    interface Presenter : MvpPresenter<View>
}
