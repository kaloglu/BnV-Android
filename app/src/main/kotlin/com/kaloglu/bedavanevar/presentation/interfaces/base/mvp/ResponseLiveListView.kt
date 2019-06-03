package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface ResponseLiveListView : MvpView {
    fun onLoading()
    fun <M : BaseModel> onSuccess(data: List<M>)
    fun onEmpty()
    fun <M : BaseModel> onError(errorMessage: String?, data: List<M>?)
}
