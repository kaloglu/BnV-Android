package com.kaloglu.bedavanevar.injection.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kaloglu.bedavanevar.injection.qualifier.ActivityContext
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.base.BaseActivity
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import com.kaloglu.bedavanevar.presentation.interfaces.base.navigator.BaseFragmentNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun fragmentManager(activity: AppCompatActivity): FragmentManager =
                activity.supportFragmentManager

        @JvmStatic
        @Provides
        @PerActivity
        fun fragmentNavigator(fragmentManager: FragmentManager): FragmentNavigator =
                BaseFragmentNavigator(fragmentManager)

        @JvmStatic
        @Provides
        @PerActivity
        fun activityNavigator(activity: AppCompatActivity): ActivityNavigator =
                ActivityNavigator(activity)

    }

    @ActivityContext
    @Binds
    @PerActivity
    abstract fun context(activity: Activity): Context

    @Binds
    @PerActivity
    abstract fun activity(activity: AppCompatActivity): Activity

    @Binds
    @PerActivity
    abstract fun appCompat(activity: BaseActivity): AppCompatActivity

    @Binds
    @PerFragment
    abstract fun fragment(fragment: BaseFragment): Fragment

}
