package com.kaloglu.bedavanevar.injection.module.main

import com.kaloglu.bedavanevar.injection.module.ActivityModule
import com.kaloglu.bedavanevar.injection.module.raffle.RaffleModule
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.base.BaseActivity
import com.kaloglu.bedavanevar.mobileui.main.MainActivity
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleFragment
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleListFragment
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.main.MainContract
import com.kaloglu.bedavanevar.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun presenter(genericDependencies: GenericDependencies): MainContract.Presenter =
                MainPresenter(genericDependencies)

    }

    @Binds
    @PerActivity
    abstract fun main(activity: MainActivity): BaseActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [RaffleModule::class])
    abstract fun contributesRaffleListFragment(): RaffleListFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [RaffleModule::class])
    abstract fun contributesRaffleFragment(): RaffleFragment

}
