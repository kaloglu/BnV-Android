package com.kaloglu.bedavanevar.adapter.main

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kaloglu.bedavanevar.mobileui.base.BaseFragment
import com.kaloglu.bedavanevar.mobileui.raffle.RaffleListFragment
import com.kaloglu.bedavanevar.utils.extensions.putArgs
import java.util.*
import javax.inject.Inject

/**
 *
 */
class ViewPagerAdapter @Inject constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = ArrayList<BaseFragment>()
    /**
     * Get the current fragment
     */
    var currentFragment: BaseFragment? = null

    init {

        fragments.clear()
        fragments.add(RaffleListFragment().putArgs())
    }

    override fun getItem(position: Int): BaseFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as BaseFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}
