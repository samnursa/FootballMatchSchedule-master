package com.example.dev_bandung.footballmatchschedule.league

import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.LeagueResponse
import com.example.dev_bandung.footballmatchschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LeaguePresenter(private val view: LeagueView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLeagueList() {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLeague()),
                        LeagueResponse::class.java
                )
            }
            view.showLeagueList(data.await().leagues)
        }
    }
}