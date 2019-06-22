package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.livedata.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenuView

interface MvpListView : ResponseLiveListView, UIStateManager.UIStatesView {
    var bottomSheetMenuView: BottomSheetMenuView

    fun <M : BaseModel> onClickView(model: M, view: android.view.View)
    fun <M : BaseModel> onClickItem(model: M)
    fun <M : BaseModel> observeLiveData(liveData: QueryLiveData<M>)
}
