package com.example.dev_bandung.footballmatchschedule.detailMatch

import com.example.dev_bandung.footballmatchschedule.model.Match
import com.example.dev_bandung.footballmatchschedule.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<Match>)
    fun showImageHome(data: List<Team>)
    fun showImageAway(data: List<Team>)
}