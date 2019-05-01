package com.kaloglu.bedavanevar.injection

import android.app.Application
import com.kaloglu.bedavanevar.BedavaNevarApp
import com.kaloglu.bedavanevar.injection.module.ActivityBindingModule
import com.kaloglu.bedavanevar.injection.module.ApplicationModule
import com.kaloglu.bedavanevar.injection.module.ContextModule
import com.kaloglu.bedavanevar.injection.module.data.FirebaseModule
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ApplicationModule::class,
    ContextModule::class,
    FirebaseModule::class
])
interface ApplicationComponent : AndroidInjector<BedavaNevarApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(bedavaNevarApp: BedavaNevarApp)

}
