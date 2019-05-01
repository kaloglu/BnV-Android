package com.kaloglu.bedavanevar.presentation.base

import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerActivity
open class GenericDependencies @Inject constructor(
        open val firebaseAuth: FirebaseAuth,
        open val activityNavigator: ActivityNavigator,
        open val fragmentNavigator: FragmentNavigator
)
