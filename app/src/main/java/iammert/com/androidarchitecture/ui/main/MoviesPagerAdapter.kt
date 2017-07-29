package iammert.com.androidarchitecture.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by mertsimsek on 20/05/2017.
 */

class MoviesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(i: Int): Fragment =
            MovieListFragment.newInstance()

    override fun getCount(): Int =
            titles.size

    override fun getPageTitle(position: Int): CharSequence =
            titles[position]

    companion object {
        private val titles = arrayOf("Popular", "Science", "Comedy")
    }
}
