package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface ResponseLiveView<M : BaseModel> : MvpView {
    fun onLoading()
    fun onSuccess(data: M)
    fun onEmpty()
    fun onError(errorMessage: String?, data: M?)
}
