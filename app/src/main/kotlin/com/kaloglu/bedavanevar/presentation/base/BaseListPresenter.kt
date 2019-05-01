package com.kaloglu.bedavanevar.presentation.base

import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateType
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpListView
import com.kaloglu.bedavanevar.utils.extensions.checkInjection
import com.kaloglu.bedavanevar.utils.extensions.observe
import javax.inject.Inject

/**
 * Base implementation for presenter
 * */
abstract class BaseListPresenter<M : BaseModel, V : MvpListView<M>>
    : BasePresenter<V>(), MvpListPresenter<M, V> {

    override val genericDependencies: GenericListDependencies? = null
        get() = field.checkInjection()

    @get:Inject
    open val repository: BaseRepository<M>? = null
        get() = field.checkInjection()

    override fun attachView(view: V) {
        uiStateManager.initStates(view)
        super.attachView(view)
    }

    final override fun attachLifecycle() = getLifeCycle()
            .addObserver(this)

    final override fun detachLifecycle() = getLifeCycle()
            .removeObserver(this)

    final override fun getLifeCycle() = getView().lifecycle

    override fun getUIState(state: UIStateType) = uiStateManager.getState(state)

    override fun observe() {
        getView().onLoading()
        repository!!.get(null).observe(getView())
    }

    override fun remove(model: M) {
        repository!!.remove(model.id)
    }

}
