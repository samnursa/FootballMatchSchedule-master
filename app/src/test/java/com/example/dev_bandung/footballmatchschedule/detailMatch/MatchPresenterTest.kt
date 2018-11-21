package com.example.dev_bandung.footballmatchschedule.detailMatch

import com.example.dev_bandung.footballmatchschedule.TestContextProvider
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.Match
import com.example.dev_bandung.footballmatchschedule.model.MatchResponse
import com.example.dev_bandung.footballmatchschedule.model.Team
import com.example.dev_bandung.footballmatchschedule.model.TeamResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private
    lateinit var view: DetailMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailMatch() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val id = "576580"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(id)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getDetailMatch(id)

        verify(view).showLoading()
        verify(view).showDetailMatch(match)
        verify(view).hideLoading()
    }

    @Test
    fun getTeamHome() {
        val team: MutableList<Team> = mutableListOf()
        val response = TeamResponse(team)
        val name = "Crystal Palace"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(name)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamHome(name)

        verify(view).showImageHome(team)
    }

    @Test
    fun getTeamAway() {
        val team: MutableList<Team> = mutableListOf()
        val response = TeamResponse(team)
        val name = "Tottenham"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(name)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamAway(name)

        verify(view).showImageAway(team)
    }
}