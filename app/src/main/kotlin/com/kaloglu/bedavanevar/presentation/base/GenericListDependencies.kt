package com.kaloglu.bedavanevar.presentation.base

import com.google.firebase.auth.FirebaseUser
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerFragment
class GenericListDependencies @Inject constructor(
        loginUser: FirebaseUser?,
        activityNavigator: ActivityNavigator,
        fragmentNavigator: FragmentNavigator,
        var uiStateManager: UIStateManager
) : GenericDependencies(loginUser, activityNavigator, fragmentNavigator)
