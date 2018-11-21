package com.example.dev_bandung.footballmatchschedule.detailMatch

import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.MatchResponse
import com.example.dev_bandung.footballmatchschedule.model.TeamResponse
import com.example.dev_bandung.footballmatchschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(private val view: DetailMatchView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailMatch(name:String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getMatchDetail(name)),
                        MatchResponse::class.java
                )
            }
            view.showDetailMatch(data.await().events)
            view.hideLoading()
        }
    }

    fun getTeamHome(name:String?) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(name)),
                        TeamResponse::class.java
                )
            }
            view.showImageHome(data.await().teams)
        }
    }

    fun getTeamAway(name:String?) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(name)),
                        TeamResponse::class.java
                )
            }
            view.showImageAway(data.await().teams)
        }
    }
}