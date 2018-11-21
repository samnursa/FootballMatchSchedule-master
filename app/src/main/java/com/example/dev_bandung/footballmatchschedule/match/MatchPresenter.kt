package com.example.dev_bandung.footballmatchschedule.match

import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.MatchResponse
import com.example.dev_bandung.footballmatchschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchList(id:String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLastMatch(id)),
                        MatchResponse::class.java
                )
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

    fun getNextMatchList(id:String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatch(id)),
                        MatchResponse::class.java
                )
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }
}