package com.kaloglu.bedavanevar.injection.module.raffle

import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleFragment
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleListFragment
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.base.GenericListDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.presentation.raffle.RaffleListPresenter
import com.kaloglu.bedavanevar.presentation.raffle.RafflePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RaffleModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFragment
        fun listPresenter(
                repository: RaffleRepository,
                genericDependencies: GenericListDependencies
        ): RaffleContract.ListPresenter =
                RaffleListPresenter(repository, genericDependencies)

        @JvmStatic
        @Provides
        @PerFragment
        fun presenter(
                repository: RaffleRepository,
                genericDependencies: GenericDependencies
        ): RaffleContract.Presenter =
                RafflePresenter(repository, genericDependencies)

    }

    @Binds
    @PerFragment
    abstract fun raffleList(fragment: RaffleListFragment): BaseFragment

    @Binds
    @PerFragment
    abstract fun raffle(fragment: RaffleFragment): BaseFragment

}
