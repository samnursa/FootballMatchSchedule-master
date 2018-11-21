package com.example.dev_bandung.footballmatchschedule.player

import android.util.Log
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.PlayerResponse
import com.example.dev_bandung.footballmatchschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(id:String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayer(id)),
                        PlayerResponse::class.java
                )
            }
            view.showPlayerList(data.await().player)
            view.hideLoading()
        }
    }
}