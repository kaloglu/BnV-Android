package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenuView

interface MvpListView<M : BaseModel> : ResponseLiveListView<M>, UIStateManager.UIStatesView {
    var bottomSheetMenuView: BottomSheetMenuView<M>

    fun onClickView(model: M, view: android.view.View)
    fun onClickItem(model: M)
}
