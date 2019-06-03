package com.kaloglu.bedavanevar.injection.module

import com.kaloglu.bedavanevar.BedavaNeVarMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
abstract class ApplicationBindingModule {

    @Singleton
    @ContributesAndroidInjector(modules = [ContextModule::class])
    abstract fun contributesService(): BedavaNeVarMessagingService

}
