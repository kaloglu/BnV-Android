package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
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

    @UiThread
    fun onPresenterAttached()

    @UiThread
    fun onPresenterDetached()

    @UiThread
    fun getMvpActivity(): BaseMvpActivity<*, *>

}
