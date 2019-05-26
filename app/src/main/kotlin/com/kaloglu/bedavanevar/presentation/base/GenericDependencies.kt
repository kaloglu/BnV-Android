package com.kaloglu.bedavanevar.presentation.base

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.data.repository.user.UserRepository
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerActivity
open class GenericDependencies @Inject constructor(
        open val activityNavigator: ActivityNavigator,
        open val fragmentNavigator: FragmentNavigator,
        open val localStorage: LocalStorage,
        open val userRepository: UserRepository
) {
    var loginUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
}
