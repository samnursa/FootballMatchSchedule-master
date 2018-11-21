package com.example.dev_bandung.footballmatchschedule.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.detailMatch.DetailLastMatch
import com.example.dev_bandung.footballmatchschedule.detailTeam.DetailTeamActivity
import com.example.dev_bandung.footballmatchschedule.league.LeaguePresenter
import com.example.dev_bandung.footballmatchschedule.league.LeagueView
import com.example.dev_bandung.footballmatchschedule.model.League
import com.example.dev_bandung.footballmatchschedule.model.Team
import com.example.dev_bandung.footballmatchschedule.utils.invisible
import com.example.dev_bandung.footballmatchschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamActivity : Fragment(), TeamView, LeagueView {

    private var categories: MutableList<League> = mutableListOf()
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var v: View
    private var leagueName : String = "English Premier League"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.activity_team, container, false)
        adapter = TeamAdapter(ctx, teams){
            startActivity<DetailTeamActivity>("team" to it)
        }
        v.listTeam.layoutManager = LinearLayoutManager(context)
        v.listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        var leaguePresenter = LeaguePresenter(this,request, gson)
        leaguePresenter.getLeagueList()
        var presenter = TeamPresenter(this, request, gson)

        v.spnLeagueTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = categories[position].strLeague.toString()
                presenter.getTeamList(leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        v.swipeRefreshTeam.onRefresh {
            presenter.getTeamList(leagueName)
        }
        return v
    }

    override fun showLeagueList(data: List<League>) {
        categories.clear()
        categories.addAll(data)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, data)
        v.spnLeagueTeam.adapter = spinnerAdapter
    }

    override fun showLoading() {
        v.progressBarTeam.visible()
    }

    override fun hideLoading() {
        v.progressBarTeam.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        v.swipeRefreshTeam.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
