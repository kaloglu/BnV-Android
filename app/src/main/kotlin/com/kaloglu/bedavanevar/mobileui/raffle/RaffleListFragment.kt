package com.kaloglu.bedavanevar.mobileui.raffle

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.adapter.raffle.RaffleListAdapter
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.enums.Status
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpListFragment
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateType
import com.kaloglu.bedavanevar.presentation.interfaces.raffle.RaffleContract
import com.kaloglu.bedavanevar.utils.extensions.setItemClickListener
import com.kaloglu.bedavanevar.utils.extensions.setViewClickListener
import com.kaloglu.bedavanevar.utils.extensions.setup
import kotlinx.android.synthetic.main.fragment_raffle_list.*
import kotlinx.android.synthetic.main.raffle_list_content.*
import kotlinx.android.synthetic.main.raffle_list_empty.*
import kotlinx.android.synthetic.main.raffle_list_loading.*

class RaffleListFragment
    : BaseMvpListFragment<Raffle, RaffleContract.ListView, RaffleContract.ListPresenter>()
        , RaffleContract.ListView {

    private lateinit var adapter: RaffleListAdapter

    override val resourceLayoutId = R.layout.fragment_raffle_list

    override fun getSceneLayout(): ConstraintLayout? = raffleListScene

    override fun getContainer(uiStateType: UIStateType) = when (uiStateType) {
        UIStateType.LOADING -> loading
        UIStateType.EMPTY -> empty
        UIStateType.CONTENT -> content
        UIStateType.ERROR -> null
    }

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)

        adapter = recyclerViewRaffleList
                .setup(RaffleListAdapter())

        adapter
                .setItemClickListener(::onClickItem)
                .setViewClickListener(::onClickView)

        textViewEmptyMessage.setOnClickListener {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
            AuthUI.getInstance().signOut(context!!).addOnSuccessListener(presenter.signOut())
//            presenter.linkUserAccount()
        }
    }

    override fun <M : BaseModel> observeLiveData(liveData: QueryLiveData<M>) {
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

    override fun <M : BaseModel> onSuccess(data: List<M>) {
        super.onSuccess(data)
        adapter.items = data.map {
            it as Raffle
        }
    }

    @SuppressLint("InflateParams")
    override fun <M : BaseModel> onClickView(model: M, view: View) {
        when (view.id) {
            R.id.raffleMoreButton -> {
                bottomSheetMenuView.show(model)
            }
        }
    }

}
