package com.kaloglu.bedavanevar.mobileui.raffle

import android.annotation.SuppressLint
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.adapter.raffle.RaffleListAdapter
import com.kaloglu.bedavanevar.domain.model.Raffle
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

        fab.setOnClickListener {
            presenter.createRaffle()
        }
    }

    override fun onSuccess(data: List<Raffle>) {
        super.onSuccess(data)
        adapter.items = data
    }

    @SuppressLint("InflateParams")
    override fun onClickView(model: Raffle, view: View) {
        when (view.id) {
            R.id.raffleMoreButton -> {
                bottomSheetMenuView.show(model)
            }
        }
    }

}
