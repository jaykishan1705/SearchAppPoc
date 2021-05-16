package com.example.searchapp.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.searchapp.repository.model.BlocksItem
import com.example.searchapp.ui.search.activities.ActivityListFragment

class ViewPagerAdapter(private val fm: FragmentManager) :
    androidx.fragment.app.FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    override fun getCount(): Int {

        return mFragmentList.size
    }

    fun addFrag(blocksItems: List<BlocksItem>) {
        clearFragment()
        blocksItems.forEach {
            it.name?.let { it1 -> addFrag(ActivityListFragment.newInstance(it), it1) }
        }
    }

    private fun clearFragment() {
        val transaction: FragmentTransaction = fm.beginTransaction()
        for (fragment in mFragmentList) {
            transaction.remove(fragment)
        }
        mFragmentList.clear()
        mFragmentTitleList.clear()
        transaction.commitAllowingStateLoss()
    }
}