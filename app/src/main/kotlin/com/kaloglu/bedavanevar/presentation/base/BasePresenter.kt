package com.kaloglu.bedavanevar.presentation.base

import androidx.annotation.CallSuper
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseUiException
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.UserDetail
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.bedavanevar.presentation.interfaces.base.mvp.MvpView
import com.kaloglu.bedavanevar.utils.extensions.checkInjection
import java.lang.ref.WeakReference

/**
 * Base implementation for presenter
 * */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {
    override val genericDependencies: GenericDependencies? = null
        get() = field.checkInjection()

    private var viewRef: WeakReference<V>? = null

    private val authListener: FirebaseAuth.AuthStateListener = FirebaseAuth.AuthStateListener {
        loginUser = it.currentUser
        if (loginUser == null) {
            onLogout()
        } else {
            onLogin()
        }
    }

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: V) {
        viewRef = WeakReference(view)
        view.onPresenterAttached()
    }

    @CallSuper
    final override fun detachView() {
        getView()?.onPresenterDetached()
        viewRef?.clear()
        viewRef = null
    }

    @CallSuper
    override fun onLogin() {
        fillUserData()
    }

    override fun getView() = when {
        isViewAttached() -> viewRef?.get()!!
        else -> null
    }

    override fun signOut() = OnSuccessListener<Void> {
        activityNavigator
                .toSplashScreen()
                .finishThis()
                .navigate()
    }

    override fun addAuthListener() {
        FirebaseAuth.getInstance().addAuthStateListener(authListener)
    }

    override fun removeAuthListener() {
        FirebaseAuth.getInstance().removeAuthStateListener(authListener)
    }

    override fun onLogout() {
        removeAuthListener()
        activityNavigator
                .toSignInActivity(requestCodeForSignIn)
                .navigate()
    }

    override fun fillUserData() {
        loginUser?.run {
            val userDetail = UserDetail(
                    id = uid,
                    fullname = displayName,
                    email = email,
                    gsm = phoneNumber,
                    deviceToken = localStorage.deviceToken,
                    profilePicUrl = photoUrl.toString()
            )

            userRepository
                    .add(userDetail)
                    .addOnSuccessListener {
                        userDetail.checkUnregisterToken()
                    }

        }
    }

    override fun removeUnregisteredToken(deviceToken: String) {
        userRepository.removeDeviceToken(deviceToken)
    }

    final override fun isViewAttached() = viewRef != null && viewRef?.get() != null

    final override fun showFireBaseAuthError(firebaseUiException: FirebaseUiException) {
        getView()?.showSnackbar(
                when (firebaseUiException.errorCode) {
                    ErrorCodes.UNKNOWN_ERROR -> R.string.unknown_error
                    ErrorCodes.NO_NETWORK -> R.string.no_internet_connection
                    ErrorCodes.PLAY_SERVICES_UPDATE_CANCELLED -> R.string.common_google_play_services_updating_text
                    ErrorCodes.DEVELOPER_ERROR -> R.string.developer_error
                    ErrorCodes.PROVIDER_ERROR -> R.string.provider_error
                    ErrorCodes.ANONYMOUS_UPGRADE_MERGE_CONFLICT -> R.string.anonymous_upgrade_merge_error
                    ErrorCodes.EMAIL_MISMATCH_ERROR -> R.string.email_mismatch_error
                    else -> R.string.unknown_sign_in_response
                }
        )
    }

    final override fun getLifeCycle() = getView()?.lifecycle

    final override fun attachLifecycle() {
        getLifeCycle()?.addObserver(this)
    }

    final override fun detachLifecycle() {
        getLifeCycle()?.removeObserver(this)
    }

    final override fun showFragment(fragment: BaseFragment?) {
        fragment?.let(fragmentNavigator::showFragment)
    }

    private fun UserDetail.checkUnregisterToken() {
        getView()?.findUnregisteredToken(
                userRepository
                        .getDeviceToken(
                                Filters()
                                        .addEqualTo(
                                                field = "deviceToken",
                                                value = deviceToken
                                        )
                        )
        )

    }

}
