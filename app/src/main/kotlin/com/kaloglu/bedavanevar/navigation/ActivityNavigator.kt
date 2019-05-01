package com.kaloglu.bedavanevar.navigation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.kaloglu.bedavanevar.BuildConfig
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.mobileui.main.MainActivity
import com.kaloglu.bedavanevar.mobileui.splash.SplashActivity
import com.kaloglu.bedavanevar.utils.extensions.createIntent
import java.util.*
import javax.inject.Inject

@PerActivity
class ActivityNavigator @Inject constructor(val activity: AppCompatActivity) {

    fun finishCurrentActivity() =
            NavigationCreator(activity).finishThis()

    fun openExternalUrl(url: String) =
            NavigationCreator(activity).intent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    fun toSignInActivity(requestCodeForSignIn: Int): NavigationCreator {

        val providerList =
                Arrays.asList(
                        AuthUI.IdpConfig.GoogleBuilder().build()
                )

        val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                .setAvailableProviders(providerList)
                .build()

        return NavigationCreator(activity)
                .intent(intent)
                .forResult(requestCodeForSignIn)
    }

    @JvmOverloads
    fun toMainActivity(extraBuilder: (Intent.() -> Unit) = {}) =
            NavigationCreator(activity).intent(activity.createIntent<MainActivity>(extraBuilder))

    @JvmOverloads
    fun toSplashScreen(extraBuilder: (Intent.() -> Unit) = {}) =
            NavigationCreator(activity).intent(activity.createIntent<SplashActivity>(extraBuilder))

}
