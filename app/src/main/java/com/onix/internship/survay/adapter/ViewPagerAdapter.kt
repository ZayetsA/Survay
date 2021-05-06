package com.onix.internship.survay.adapter

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private var fragmentList1: ArrayList<Fragment> = ArrayList()
    private var fragmentTitleList1: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList1[position]
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList1[position]
    }

    override fun getCount(): Int {
        return fragmentList1.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList1.add(fragment)
        fragmentTitleList1.add(title)
    }
}