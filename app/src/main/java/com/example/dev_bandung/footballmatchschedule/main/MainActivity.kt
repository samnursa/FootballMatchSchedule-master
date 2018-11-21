package com.example.dev_bandung.footballmatchschedule.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.R.id.*
import com.example.dev_bandung.footballmatchschedule.favorite.FavoriteFragment
import com.example.dev_bandung.footballmatchschedule.team.TeamActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matchs -> {
                    loadTabsMatchFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matchs
    }

    private fun loadTabsMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TabMatch(), TabMatch::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamActivity(), TeamActivity::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TabFavorite(), TabFavorite::class.java.simpleName)
                    .commit()
        }
    }
}
