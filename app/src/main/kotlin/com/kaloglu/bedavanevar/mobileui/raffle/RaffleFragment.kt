package com.kaloglu.bedavanevar.mobileui.raffle

import android.view.View
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.empty
import kotlinx.android.synthetic.main.fragment_raffle.*

class RaffleFragment : BaseMvpFragment<RaffleContract.View, RaffleContract.Presenter>(),
        RaffleContract.View {

    override lateinit var submitButtonView: View

    override val resourceLayoutId = R.layout.fragment_raffle

    private lateinit var model: Raffle

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)
        arguments?.let {
            model = (it["model"] as Raffle)
        }
        toolbar_container.title = model.title
//        raffleForm_Name.onActionResInSoftKeyboard(R.integer.create_raffle) { submitButtonView.callOnClick() }
        setSubmitButton(raffleForm_Submit) {
            presenter.submitForm()
        }
    }

    override fun getRaffleName(): String = String.empty

}
