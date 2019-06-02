package com.kaloglu.bedavanevar.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.firebase.ui.auth.FirebaseUiException
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.data.repository.user.UserRepository
import com.kaloglu.bedavanevar.domain.model.UserDetail
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies

interface MvpPresenter<V : MvpView> : LifecycleObserver {

    val genericDependencies: GenericDependencies?

    var loginUser: FirebaseUser?
        get() = genericDependencies!!.loginUser
        set(value) {
            genericDependencies?.loginUser = value
        }

    val localStorage: LocalStorage
        get() = genericDependencies!!.localStorage

    val userRepository: UserRepository
        get() = genericDependencies!!.userRepository

    val activityNavigator: ActivityNavigator
        get() = genericDependencies!!.activityNavigator

    val fragmentNavigator: FragmentNavigator
        get() = genericDependencies!!.fragmentNavigator

    val requestCodeForSignIn: Int
        get() = 9999

    val requestCodeForLinking: Int
        get() = 9998

    @UiThread
    fun attachView(view: V)

    @UiThread
    fun detachView()

    /**
     * Gets the attached view. You should to [isViewAttached] to avoid exceptions.
     *
     * @return the view if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */
    @UiThread
    fun getView(): V?

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false if no view is attached
     */
    @UiThread
    fun isViewAttached(): Boolean

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun attachLifecycle()

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachLifecycle()

    @UiThread
    fun getLifeCycle(): Lifecycle?

    @UiThread
    fun showFragment(fragment: BaseFragment?)

    @UiThread
    fun signOut(): OnSuccessListener<Void>

    @UiThread
    fun onLogin()

    fun linkAccount(authCredential: AuthCredential)

    @UiThread
    fun onLogout()

    @UiThread
    fun showFireBaseAuthError(firebaseUiException: FirebaseUiException)

    fun removeUnregisteredToken(deviceToken: String)

    fun fillUserData()

    fun addAuthListener()
    fun removeAuthListener()
    fun updateUser(userDetail: UserDetail, newUserDetail: UserDetail)
    fun addUser(userDetail: UserDetail)
    fun linkUserAccount()
}
