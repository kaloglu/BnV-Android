package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.presentation.base.GenericListDependencies
import com.kaloglu.bedavanevar.viewobjects.Resource

interface MvpListPresenter<V : MvpListView>
    : MvpPresenter<V>, UIStateManager.UIStatesPresenter {

    override val genericDependencies: GenericListDependencies?

    val uiStateManager: UIStateManager
        get() = genericDependencies!!.uiStateManager

    @UiThread
    fun <M : BaseModel> remove(model: M, onComplete: (String, Exception?) -> Unit = { _: String, _: Exception? -> })

    @UiThread
    fun <M : BaseModel> add(model: M, onComplete: (M, Exception?) -> Unit = { _: M, _: Exception? -> })

    @UiThread
    fun <M : BaseModel> openDetail(model: M)

    fun <M : BaseModel> getData(): QueryLiveData<M>
}
