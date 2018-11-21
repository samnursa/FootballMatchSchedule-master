package com.example.dev_bandung.footballmatchschedule.match

import com.example.dev_bandung.footballmatchschedule.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}