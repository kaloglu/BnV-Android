package com.kaloglu.bedavanevar.presentation.interfaces.base.navigator

import androidx.fragment.app.FragmentManager
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.navigation.FragmentNavigator
import javax.inject.Inject

class BaseFragmentNavigator @Inject constructor(fragmentManger: FragmentManager)
    : FragmentNavigator(fragmentManger, R.id.fragment_container)
