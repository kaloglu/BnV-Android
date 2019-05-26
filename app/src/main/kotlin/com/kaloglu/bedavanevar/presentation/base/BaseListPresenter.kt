package com.kaloglu.bedavanevar.presentation.base

import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateType
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView
import com.kaloglu.bedavanevar.utils.extensions.checkInjection
import javax.inject.Inject

/**
 * Base implementation for presenter
 * */
abstract class BaseListPresenter<V : MvpListView>
    : BasePresenter<V>(), MvpListPresenter<V> {

    override val genericDependencies: GenericListDependencies? = null
        get() = field.checkInjection()

    @get:Inject
    open val repository: BaseRepository? = null
        get() = field.checkInjection()

    override fun attachView(view: V) {
        uiStateManager.initStates(view)
        super.attachView(view)
    }

    override fun getUIState(state: UIStateType) = uiStateManager.getState(state)

    override fun <M : BaseModel> getData(): QueryLiveData<M> = repository!!.get(null)

    override fun <M : BaseModel> remove(model: M) =
            repository!!.remove(model.id)

    override fun <M : BaseModel> add(model: M) =
            repository!!.add(model)

}
