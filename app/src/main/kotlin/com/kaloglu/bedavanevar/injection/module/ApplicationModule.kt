package com.kaloglu.bedavanevar.injection.module

import android.app.Application
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.kaloglu.bedavanevar.BedavaNeVarMessagingService
import com.kaloglu.bedavanevar.injection.qualifier.ApplicationContext
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

//    @Module
//    companion object {
//
//        @JvmStatic
//        @Provides
//        @PerApplication
//        fun provideloginUser(): FirebaseUser? = FirebaseAuth.getInstance().currentUser
//
//    }

    @ApplicationContext
    @Binds
    @PerApplication
    abstract fun messagingService(service: BedavaNeVarMessagingService): FirebaseMessagingService

    @ApplicationContext
    @Binds
    @PerApplication
    abstract fun bindApplication(application: Application): Context

}
