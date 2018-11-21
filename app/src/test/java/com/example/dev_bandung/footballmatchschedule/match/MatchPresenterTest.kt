package com.example.dev_bandung.footballmatchschedule.match

import com.example.dev_bandung.footballmatchschedule.TestContextProvider
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.api.TheSportDBApi
import com.example.dev_bandung.footballmatchschedule.model.Match
import com.example.dev_bandung.footballmatchschedule.model.MatchResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val id = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(id)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(id)

        verify(view).showLoading()
        verify(view).showMatchList(match)
        verify(view).hideLoading()
    }

    @Test
    fun getNextMatchList() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val id = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(id)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getNextMatchList(id)

        verify(view).showLoading()
        verify(view).showMatchList(match)
        verify(view).hideLoading()
    }
}