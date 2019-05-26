package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import com.google.android.gms.tasks.Task
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.presentation.base.GenericListDependencies

interface MvpListPresenter<V : MvpListView>
    : MvpPresenter<V>, UIStateManager.UIStatesPresenter {

    override val genericDependencies: GenericListDependencies?

    val uiStateManager: UIStateManager
        get() = genericDependencies!!.uiStateManager

    @UiThread
    fun <M : BaseModel> remove(model: M): Task<Void>

    @UiThread
    fun <M : BaseModel> add(model: M): Task<Void>

    @UiThread
    fun <M : BaseModel> openDetail(model: M)

    fun <M : BaseModel> getData(): QueryLiveData<M>
}
