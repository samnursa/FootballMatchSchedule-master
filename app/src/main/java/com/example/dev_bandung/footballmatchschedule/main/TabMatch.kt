package com.example.dev_bandung.footballmatchschedule.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R

class TabMatch : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.activity_tabs, container, false)
        viewPager = view.findViewById(R.id.viewpager_match)
        tabs = view.findViewById(R.id.tabs_match)

        val fragmentAdapter = PageMatchAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        return view
    }
}
