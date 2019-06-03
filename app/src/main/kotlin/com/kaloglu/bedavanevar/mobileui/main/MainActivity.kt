package com.kaloglu.bedavanevar.mobileui.main

import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.adapter.main.ViewPagerAdapter
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleListFragment
import com.kaloglu.bedavanevar.presentation.interfaces.main.MainContract
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var adapter: ViewPagerAdapter

    override val contentResourceId = R.layout.activity_main

    override val containedFragment = RaffleListFragment()

    override val snackbarLayoutId = R.id.fragment_container

    override fun initUserInterface() = Unit

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount < 1)
            presenter.onClearFragmentContainer()
    }

    override fun showContentContainer(show: Boolean) = Unit


}
