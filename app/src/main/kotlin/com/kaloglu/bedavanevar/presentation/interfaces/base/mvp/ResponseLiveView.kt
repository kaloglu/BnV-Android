package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import com.kaloglu.bedavanevar.domain.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface ResponseLiveView : MvpView {
    fun onLoading()
    fun <M : BaseModel> onSuccess(data: M)
    fun onEmpty()
    fun <M : BaseModel> onError(errorMessage: String?, data: M?)
    fun <M : BaseModel> observeLiveData(liveData: DocumentLiveData<M>)
}
