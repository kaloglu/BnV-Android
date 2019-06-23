package com.kaloglu.bedavanevar.mobileui.raffle

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.enums.Status
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.*
import com.kaloglu.bedavanevar.utils.extensions.GenericExtensions.LOCALE_TR
import com.kaloglu.bedavanevar.viewobjects.CalculatedResource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_raffle_fragment.*
import kotlinx.android.synthetic.main.header_raffle_fragment.*

class RaffleFragment : BaseMvpFragment<Raffle, RaffleContract.View, RaffleContract.Presenter>(),
        RaffleContract.View {
    override val resourceLayoutId = R.layout.fragment_raffle

    override lateinit var model: Raffle

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)
        arguments?.let {
            model = (it["model"] as Raffle)
            model.bindViewModel()
        }

    }

    override fun getRaffleName(): String = String.empty

    override fun <M : BaseModel> observeLiveData(liveData: DocumentLiveData<M>) {
        liveData.observe(
                this,
                Observer {
                    when (it?.status) {
                        Status.LOADING -> onLoading()
                        Status.SUCCESS -> onSuccess(it.data!!)
                        Status.EMPTY -> onEmpty()
                        Status.ERROR -> onError(it.message, it.data)
                        null -> TODO("should define ${it?.status}")
                    }
                }
        )

    }

    override fun onLoading() = Unit

    override fun <M : BaseModel> onError(errorMessage: String?, data: M?) {
        errorMessage?.let { showSnackbar(it) }
    }

    override fun onEmpty() = Unit

    override fun <M : BaseModel> onSuccess(data: M) {
        data as Raffle
        data.bindViewModel()
    }

    override fun onPresenterAttached() {
        observeLiveData(presenter.getData(model.id))
        observeUserAttendanceInfo(presenter.getAttendanceInfo())
    }

    private fun observeUserAttendanceInfo(liveData: MediatorLiveData<CalculatedResource?>) {
        liveData.observe(
                this,
                Observer {
                    when (it) {
                        is CalculatedResource.EnrollCount -> textViewEnrollCount.text = String.format(getString(R.string.enroll_count_text, it.result)).toCompactHtml()
                        is CalculatedResource.TicketCount -> {
                            textViewTicketCount.text = String.format(getString(R.string.ticket_count_text, it.result)).toCompactHtml()

                            it.result as Int

                            buttonEnrollRaffle.show(it.result > 0)
                        }
                    }
                }
        )
    }

    private fun Raffle.bindViewModel() {

        toolbar_container.title = title.toCompactHtml()
        raffleDescription.text = description.toCompactHtml()

        productInfo?.run {
            if (images.isNotNullOrEmpty())
                Picasso.get().load(images[0].path).into(imageViewRaffle)

            textViewProductInfo.text = String.format(LOCALE_TR, getString(R.string.product_info_text, count, unit, name)).toCompactHtml()
            textViewUnitPrice.text = String.format(LOCALE_TR, getString(R.string.unit_price_text, unitPrice)).toCompactHtml()
        }

        rules?.run {
            textViewMaxAttendee.text = String.format(LOCALE_TR, getString(R.string.max_attendee_text, maxAttendee)).toCompactHtml()
            textViewMaxAttendByUser.text = String.format(LOCALE_TR, getString(R.string.max_attend_by_user_text, maxAttendByUser)).toCompactHtml()
        }

        textViewStartDate.show(startDate >= currentTime())
        textViewStartDate.text = String.format(getString(R.string.start_date_text, startDate.toFormattedDate())).toCompactHtml()

        textViewEndDate.show(startDate < currentTime())
        textViewEndDate.text = String.format(getString(R.string.end_date_text, endDate.toFormattedDate())).toCompactHtml()

        buttonEnrollRaffle.setOnClickListener {
            presenter.enrollRaffle(model) {
                showSnackbar("Tebrikler çekilişe katıldınız!")
            }
        }

    }

}
