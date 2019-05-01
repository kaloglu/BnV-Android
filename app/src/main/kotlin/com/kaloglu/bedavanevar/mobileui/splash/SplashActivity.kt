package com.kaloglu.bedavanevar.mobileui.splash

import android.os.Handler
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.bedavanevar.presentation.interfaces.splash.SplashContract

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

    override val contentResourceId = R.layout.activity_splash

    override val snackbarLayoutId: Int = R.id.sign_in_container

    override fun onPresenterAttached() {
        Handler().postDelayed({
            super.onPresenterAttached()
        }, 2000)
    }
}
