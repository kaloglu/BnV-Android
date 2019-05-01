package com.kaloglu.bedavanevar

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.kaloglu.bedavanevar.injection.DaggerApplicationComponent
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import javax.inject.Inject


@PerApplication
class BedavaNevarApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Fabric.with(
                Fabric.Builder(this)
                        .kits(Crashlytics(), Answers())
                        .debuggable(BuildConfig.DEBUG)
                        .appIdentifier(BuildConfig.APPLICATION_ID)
                        .build()
        )

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}
