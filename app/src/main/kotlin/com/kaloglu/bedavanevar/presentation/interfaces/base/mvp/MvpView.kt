package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.lifecycle.LifecycleOwner
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.livedata.QueryLiveData
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.domain.model.UserDetail
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpActivity

interface MvpView : LifecycleOwner {

    @UiThread
    fun getContext(): Context?

    @UiThread
    fun showSnackbar(@StringRes messageId: Int)

    @UiThread
    fun showSnackbar(message: String)

    @UiThread
    fun <V : MvpView> V.handleSignInResult(data: Intent?, resultCode: Int)

    fun <V : MvpView> V.handleLinkingResult(data: Intent?, resultCode: Int)

    @UiThread
    fun onPresenterAttached()

    @UiThread
    fun onPresenterDetached()

    @UiThread
    fun getMvpActivity(): BaseMvpActivity<*, *>

    fun findUnregisteredToken(liveData: QueryLiveData<DeviceToken>)

    fun findRegisteredUser(liveData: DocumentLiveData<UserDetail>, newUserInfo: UserDetail)

}
