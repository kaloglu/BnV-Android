package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import android.content.Intent
import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.lifecycle.LifecycleOwner
import com.kaloglu.bedavanevar.domain.model.Raffle

interface MvpView : LifecycleOwner {

    @UiThread
    fun showSnackbar(@StringRes messageId: Int)

    @UiThread
    fun showSnackbar(message: String)

    @UiThread
    fun handleSignInResult(data: Intent?, resultCode: Int)

    @UiThread
    fun onPresenterAttached()

    @UiThread
    fun onPresenterDetached()

    @UiThread
    fun getMvpActivity(): MvpView
}
