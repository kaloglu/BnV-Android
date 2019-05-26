package com.kaloglu.bedavanevar.presentation.interfaces.splash

import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpView

interface SplashContract {

    interface View : MvpView {
//        fun findUnregisteredToken(liveData: QueryLiveData<DeviceToken>)
    }

    interface Presenter : MvpPresenter<View> {
//        fun removeUnregisteredToken(id: String)
    }
}
