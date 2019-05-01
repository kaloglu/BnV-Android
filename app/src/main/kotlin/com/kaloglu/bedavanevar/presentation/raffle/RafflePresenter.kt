package com.kaloglu.bedavanevar.presentation.raffle

import com.kaloglu.bedavanevar.data.repository.raffle.RaffleRepository
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import javax.inject.Inject

class RafflePresenter @Inject constructor(
        val repository: RaffleRepository,
        override val genericDependencies: GenericDependencies?
) : BasePresenter<RaffleContract.View>(), RaffleContract.Presenter {

    override fun isFormValid(): Boolean = true

    override fun canSubmitForm(): Boolean = when {
        getView().getRaffleName().isEmpty() -> false
        else -> true
    }

    override fun onSubmitForm() {
//        val model = Raffle(getView().getRaffleName())
//        repository.add(model).addOnCompleteListener {
//            when {
//                it.isSuccessful -> {
//                    getView().showSnackbar(R.string.raffle_form_success_message)
//                    fragmentNavigator.popBackStack()
//                }
//                else -> getView().showSnackbar(it.exception!!.localizedMessage)
//            }
//        }
    }


}
