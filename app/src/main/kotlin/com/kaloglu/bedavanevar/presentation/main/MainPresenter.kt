package com.kaloglu.bedavanevar.presentation.main

import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleFragment
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.main.MainContract
import com.kaloglu.bedavanevar.utils.extensions.putArgs
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor(override val genericDependencies: GenericDependencies?)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onClearFragmentContainer() = getView().showContentContainer(true)

    override fun onFillFragmentContainer() = getView().showContentContainer(false)

    override fun newRaffle() {
        fragmentNavigator
                .setCallback(this)
                .showFragment(RaffleFragment().putArgs())
    }
}
