package com.kaloglu.bedavanevar.presentation.base

import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerFragment
class GenericListDependencies @Inject constructor(
        override val firebaseAuth: FirebaseAuth,
        override val activityNavigator: ActivityNavigator,
        override val fragmentNavigator: FragmentNavigator,
        var uiStateManager: UIStateManager
) : GenericDependencies(firebaseAuth, activityNavigator, fragmentNavigator)
