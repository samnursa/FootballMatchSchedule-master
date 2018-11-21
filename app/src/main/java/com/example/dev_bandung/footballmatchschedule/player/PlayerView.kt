package com.example.dev_bandung.footballmatchschedule.player

import com.example.dev_bandung.footballmatchschedule.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}