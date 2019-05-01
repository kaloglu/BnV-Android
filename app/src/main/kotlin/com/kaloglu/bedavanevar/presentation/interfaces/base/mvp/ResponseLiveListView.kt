package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface ResponseLiveListView<M : BaseModel> : MvpView {
    fun onLoading()
    fun onSuccess(data: List<M>)
    fun onEmpty()
    fun onError(errorMessage: String?, data: List<M>?)
}
