package com.kaloglu.bedavanevar.injection.module

import android.app.Application
import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ContextModule {
    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun providesLocalStorage(application: Application): LocalStorage =
                LocalStorage(application)

    }
}
