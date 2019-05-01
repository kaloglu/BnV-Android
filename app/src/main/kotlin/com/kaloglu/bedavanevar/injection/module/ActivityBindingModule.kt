package com.kaloglu.bedavanevar.injection.module

import com.kaloglu.bedavanevar.injection.module.main.MainModule
import com.kaloglu.bedavanevar.injection.module.splash.SplashModule
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.mobileui.main.MainActivity
import com.kaloglu.bedavanevar.mobileui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributesSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity(): MainActivity

}
