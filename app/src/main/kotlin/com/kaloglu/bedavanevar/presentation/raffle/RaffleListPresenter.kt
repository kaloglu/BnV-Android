package com.kaloglu.bedavanevar.presentation.raffle

import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleFragment
import com.kaloglu.bedavanevar.presentation.base.BaseListPresenter
import com.kaloglu.bedavanevar.presentation.base.GenericListDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.putArgs
import javax.inject.Inject

class RaffleListPresenter @Inject constructor(
        override val repository: RaffleRepository,
        override val genericDependencies: GenericListDependencies
) : BaseListPresenter<Raffle, RaffleContract.ListView>(), RaffleContract.ListPresenter {

    override fun openDetail(model: Raffle) {
        showFragment(
                RaffleFragment().putArgs {
                    putSerializable("model", model)
                }
        )
    }

    override fun createRaffle() {
//        repository.add(Raffle("new Raffle ${Date()}"))
    }
}
