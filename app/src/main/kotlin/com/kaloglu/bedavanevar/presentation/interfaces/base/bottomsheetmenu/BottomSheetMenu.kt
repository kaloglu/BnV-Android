package com.kaloglu.bedavanevar.presentation.interfaces.base.bottomsheetmenu

import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.inflate

open class BottomSheetMenu : BottomSheetMenuView {

    override lateinit var bottomSheetView: View

    override val layoutId: Int = R.layout.bottom_sheet_dialog

    override lateinit var bottomSheetDialog: BottomSheetDialog

    override fun <M : BaseModel> onClickBottomMenuItem(bottomSheetItem: M, bottomMenuItemView: View) = Unit

    override fun initBottomMenuView(viewGroup: ViewGroup) = Unit

    private fun <M : BaseModel> setAllItemViewClickListener(bottomSheetItem: M, bottomSheetView: ViewGroup) {
        bottomSheetView.forEach { view ->
            view
                    .takeIf { it.tag != null }
                    ?.takeIf { (it.tag as String) == "bottomMenuButton" }
                    ?.setOnClickListener {
                        onClickBottomMenuItem(bottomSheetItem, it)
                        bottomSheetDialog.dismiss()
                    }
        }
    }

    override fun <M : BaseModel> show(itemModel: M) {
        initBottomMenuView(bottomSheetView as ViewGroup)

        setAllItemViewClickListener(itemModel, bottomSheetView as ViewGroup)

        bottomSheetDialog.show()
    }

    override fun init(viewGroup: ViewGroup) {
        bottomSheetView = viewGroup.inflate(layoutId)

        bottomSheetDialog = BottomSheetDialog(viewGroup.context).apply {
            setContentView(bottomSheetView)
        }

    }

}