package com.kaloglu.bedavanevar.mobileui.splash

import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.enums.Status
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.bedavanevar.presentation.interfaces.splash.SplashContract
import timber.log.Timber

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {
//    override fun findUnregisteredToken(liveData: QueryLiveData<DeviceToken>) {
//        liveData.observe(this@SplashActivity, Observer {
//            if (it.status == Status.SUCCESS) {
//
//                it.data?.forEach {
//                    presenter.removeUnregisteredToken(it.id)
//                }
//            } else if (it.status == Status.EMPTY) {
//                Timber.i("alla allaaaa (%)", it.data)
//                Toast.makeText(getContext(), "Liste boş :\\", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    override val contentResourceId = R.layout.activity_splash

    override val snackbarLayoutId: Int = R.id.sign_in_container

    override fun onPresenterAttached() {
        Handler().postDelayed({
            super.onPresenterAttached()
        }, 2000)
    }
}
