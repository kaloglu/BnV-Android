package com.kaloglu.bedavanevar

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.kaloglu.bedavanevar.injection.DaggerApplicationComponent
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber

@PerApplication
class BedavaNevarApp : DaggerApplication() {

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


    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // We only provide its own Injector, the Application Injector,
        // that is the previous AppComponent
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }
}
