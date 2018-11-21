package com.example.dev_bandung.footballmatchschedule.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.dev_bandung.footballmatchschedule.favorite.FavoriteFragment

class PageFavoriteAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteFragment()
            }
            else -> {
                return FavoriteFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "MATCHES"
            else -> {
                return "TEAMS"
            }
        }
    }
}