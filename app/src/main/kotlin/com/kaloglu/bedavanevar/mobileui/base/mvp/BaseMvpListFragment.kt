package com.kaloglu.bedavanevar.mobileui.base.mvp

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateType
import com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenu
import com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenuView
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView

abstract class BaseMvpListFragment<M : BaseModel, V : MvpListView, P : MvpListPresenter<V>>
    : BaseMvpFragment<V, P>(), MvpListView {

    override var bottomSheetMenuView: BottomSheetMenuView = object : BottomSheetMenu() {
        override fun <M : BaseModel> onClickBottomMenuItem(
                bottomSheetItem: M,
                bottomMenuItemView: View
        ) {
            when (bottomMenuItemView.id) {
                R.id.bottomSheetEdit -> presenter.openDetail(bottomSheetItem)
                R.id.bottomSheetDelete -> presenter.remove(bottomSheetItem)
                else -> TODO("should override onClickBottomMenuItem(bottomMenuItem: View)")
            }

        }
    }

    override fun onLoading() = presenter.getUIState(UIStateType.LOADING)

    override fun onEmpty() = presenter.getUIState(UIStateType.EMPTY)

    @CallSuper
    override fun <M : BaseModel> onSuccess(data: List<M>) = presenter.getUIState(UIStateType.CONTENT)

    @CallSuper
    override fun <M : BaseModel> onError(errorMessage: String?, data: List<M>?) =
            presenter.getUIState(UIStateType.ERROR)

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)
        bottomSheetMenuView.init(rootView as ViewGroup)
    }

    override fun <M : BaseModel> onClickItem(model: M) = presenter.openDetail(model)

    override fun <M : BaseModel> onClickView(model: M, view: View) = Unit

    @CallSuper
    override fun onPresenterAttached() {
        super.onPresenterAttached()
        observeQuery<M>(presenter.getData())
    }

}
