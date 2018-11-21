package com.example.dev_bandung.footballmatchschedule.team

import com.example.dev_bandung.footballmatchschedule.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}