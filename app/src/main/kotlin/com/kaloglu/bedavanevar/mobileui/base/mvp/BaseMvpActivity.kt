package com.kaloglu.bedavanevar.mobileui.base.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.DocumentLiveData
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.enums.Status
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.domain.model.UserDetail
import com.kaloglu.bedavanevar.mobileui.base.BaseActivity
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpView
import javax.inject.Inject

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : BaseActivity(), MvpView {

    @Inject
    lateinit var presenter: P

    protected open val snackbarLayoutId: Int = -1

    protected open val containedFragment: BaseFragment? = null

    protected open var currentFragment: BaseFragment? = null

    protected var badgeVisible: Int = -1

    /** WeakAccess */
    override fun getMvpActivity() = this

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
        savedInstanceState ?: let {
            containedFragment?.let(presenter::showFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            presenter.requestCodeForSignIn -> this.handleSignInResult(data, resultCode)
            presenter.requestCodeForLinking -> this.handleLinkingResult(data, resultCode)
        }
    }

    override fun initUserInterface() = Unit

    override fun onPresenterDetached() = Unit

    override fun onPresenterAttached() = Unit

    override fun showSnackbar(messageId: Int) = showSnackbar(resources.getString(messageId))

    override fun showSnackbar(message: String) {
        if (snackbarLayoutId == -1)
            return

        Snackbar.make(findViewById(snackbarLayoutId), message, Snackbar.LENGTH_LONG).show()
    }

    override fun <V : MvpView> V.handleSignInResult(data: Intent?, resultCode: Int) {
        val response = IdpResponse.fromResultIntent(data)

        return when {
            response == null -> showSnackbar(R.string.sign_in_cancelled)
            response.error != null -> presenter.showFireBaseAuthError(response.error!!)
            else -> Unit
        }
    }

    override fun <V : MvpView> V.handleLinkingResult(data: Intent?, resultCode: Int) {
        val response = IdpResponse.fromResultIntent(data)

        return when (resultCode) {
            AppCompatActivity.RESULT_OK -> {
                if (response != null) {
                    response.credentialForLinking?.let(presenter::linkAccount)
                }
                return
            }
            else -> handleSignInResult(data, resultCode)
        }
    }

    override fun findUnregisteredToken(liveData: QueryLiveData<DeviceToken>) {
        liveData.observe(this, Observer {
            if (it.status == Status.SUCCESS) {
                it.data?.forEach { deviceToken ->
                    presenter.removeUnregisteredToken(deviceToken.id)
                }
            }

        })

    }

    override fun findRegisteredUser(liveData: DocumentLiveData<UserDetail>, newUserInfo: UserDetail) {
        liveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    presenter.updateUser(it.data!!, newUserInfo)
                }
                Status.EMPTY -> presenter.addUser(newUserInfo)
                else -> {
                }
            }

        })

    }

    override fun getContext(): Context? = this

    override fun onResume() {
        super.onResume()
        presenter.addAuthListener()
    }

    override fun onStop() {
        super.onStop()
        presenter.removeAuthListener()
    }
}
