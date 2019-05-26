package com.kaloglu.bedavanevar.injection.module.splash

import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.data.repository.devicetoken.DeviceTokenRepository
import com.kaloglu.bedavanevar.data.repository.user.UserRepository
import com.kaloglu.bedavanevar.injection.module.ActivityModule
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.mobileui.base.BaseActivity
import com.kaloglu.bedavanevar.mobileui.splash.SplashActivity
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.splash.SplashContract
import com.kaloglu.bedavanevar.presentation.splash.SplashPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
abstract class SplashModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun presenter(
                localStorage: LocalStorage,
                userRepository: UserRepository,
                deviceTokenRepository: DeviceTokenRepository,
                genericDependencies: GenericDependencies
        ): SplashContract.Presenter =
                SplashPresenter(
                        localStorage,
                        userRepository,
                        deviceTokenRepository,
                        genericDependencies
                )

    }

    @Binds
    @PerActivity
    abstract fun splash(activity: SplashActivity): BaseActivity

}
