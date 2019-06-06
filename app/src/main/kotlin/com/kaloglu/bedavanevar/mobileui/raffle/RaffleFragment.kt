package com.kaloglu.bedavanevar.mobileui.raffle

import android.view.View
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.*
import com.kaloglu.bedavanevar.utils.extensions.GenericExtensions.LOCALE_TR
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_raffle_fragment.*
import kotlinx.android.synthetic.main.header_raffle_fragment.*

class RaffleFragment : BaseMvpFragment<RaffleContract.View, RaffleContract.Presenter>(),
        RaffleContract.View {

    override lateinit var submitButtonView: View

    override val resourceLayoutId = R.layout.fragment_raffle

    private lateinit var model: Raffle

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)
        arguments?.let {
            model = (it["model"] as Raffle)
            model.bindViewModel()
        }

//        raffleForm_Name.onActionResInSoftKeyboard(R.integer.create_raffle) { submitButtonView.callOnClick() }
//        setSubmitButton(raffleForm_Submit) {
//            presenter.submitForm()
//        }
    }

    private fun Raffle.bindViewModel() {

        toolbar_container.title = title?.toCompactHtml()
        raffleDescription.text = description?.toCompactHtml()

        productInfo?.run {
            if (images.isNotNullOrEmpty())
                Picasso.get().load(images?.get(0)?.path).into(imageViewRaffle)

            textViewProductInfo.text = String.format(LOCALE_TR, getString(R.string.product_info_text, count, unit, name)).toCompactHtml()
            textViewUnitPrice.text = String.format(LOCALE_TR, getString(R.string.unit_price_text, unitPrice)).toCompactHtml()
        }

        rules?.run {
            textViewMaxAttendee.text = String.format(LOCALE_TR, getString(R.string.max_attendee_text, maxAttendee)).toCompactHtml()
            textViewMaxAttendByUser.text = String.format(LOCALE_TR, getString(R.string.max_attend_by_user_text, maxAttendByUser)).toCompactHtml()
        }

        textViewStartDate.show(startDate?.toDate()?.time!! >= currentTime())
        textViewStartDate.text = String.format(getString(R.string.start_date_text, startDate?.toFormattedDate())).toCompactHtml()

        textViewEndDate.show(startDate?.toDate()?.time!! < currentTime())
        textViewEndDate.text = String.format(getString(R.string.end_date_text, endDate?.toFormattedDate())).toCompactHtml()
    }

    override fun getRaffleName(): String = String.empty

}
