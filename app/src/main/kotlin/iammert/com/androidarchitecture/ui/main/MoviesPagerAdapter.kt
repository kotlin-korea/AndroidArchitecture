package iammert.com.androidarchitecture.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by mertsimsek on 20/05/2017.
 */

class MoviesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        private val titles = arrayOf("Popular", "Science", "Comedy")
    }

    override fun getItem(i: Int): Fragment {
        return MovieListFragment.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
