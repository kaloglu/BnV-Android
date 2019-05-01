package com.kaloglu.bedavanevar.presentation.splash

import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.splash.SplashContract
import javax.inject.Inject

@PerActivity
class SplashPresenter @Inject constructor(override val genericDependencies: GenericDependencies)
    : BasePresenter<SplashContract.View>(), SplashContract.Presenter {

    override fun onLogin() = activityNavigator
            .toMainActivity()
            .singleTop()
            .clearTop()
            .finishThis()
            .navigate()
}



