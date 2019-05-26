package com.kaloglu.bedavanevar.presentation.base

import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.data.repository.user.UserRepository
import com.kaloglu.bedavanevar.injection.scopes.PerFragment
import com.kaloglu.bedavanevar.mobileui.interfaces.UIStateManager
import com.kaloglu.bedavanevar.navigation.ActivityNavigator
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

@PerFragment
class GenericListDependencies @Inject constructor(
        activityNavigator: ActivityNavigator,
        fragmentNavigator: FragmentNavigator,
        localStorage: LocalStorage,
        userRepository: UserRepository,
        var uiStateManager: UIStateManager
) : GenericDependencies(activityNavigator, fragmentNavigator, localStorage, userRepository)
