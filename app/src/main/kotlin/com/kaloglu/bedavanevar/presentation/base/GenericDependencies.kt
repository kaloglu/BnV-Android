package com.kaloglu.bedavanevar.presentation.base

import com.google.firebase.auth.FirebaseUser
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerActivity
open class GenericDependencies @Inject constructor(
        open var loginUser: FirebaseUser?,
        open val activityNavigator: ActivityNavigator,
        open val fragmentNavigator: FragmentNavigator
)
