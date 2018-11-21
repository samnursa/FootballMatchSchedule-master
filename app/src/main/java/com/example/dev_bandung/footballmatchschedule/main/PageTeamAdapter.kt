package com.example.dev_bandung.footballmatchschedule.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.dev_bandung.footballmatchschedule.match.LastMatch
import com.example.dev_bandung.footballmatchschedule.overview.OverviewFragment
import com.example.dev_bandung.footballmatchschedule.player.PlayerFragment

class PageTeamAdapter(fm:FragmentManager, desc : String?, id : String?) : FragmentPagerAdapter(fm) {

    private val mDesc = desc
    private val mId = id

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OverviewFragment.newInstance(mDesc)
            }
            else -> {
                return PlayerFragment.newInstance(mId)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "OVERVIEW"
            else -> {
                return "PLAYERS"
            }
        }
    }
}