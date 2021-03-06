package com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu

import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.base.BaseModel


interface BottomSheetMenuView {
    val layoutId: Int
        get() = R.layout.bottom_sheet_dialog

    var bottomSheetDialog: BottomSheetDialog

    var bottomSheetView: View

    fun initBottomMenuView(viewGroup: ViewGroup)

    fun <M : BaseModel> onClickBottomMenuItem(bottomSheetItem: M, bottomMenuItemView: View)
    fun <M : BaseModel> show(itemModel: M)
    fun init(viewGroup: ViewGroup)
}
