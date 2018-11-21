package com.example.dev_bandung.footballmatchschedule.league

import com.example.dev_bandung.footballmatchschedule.model.League

interface LeagueView {
    fun showLeagueList(data: List<League>)
}