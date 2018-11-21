package com.example.dev_bandung.footballmatchschedule.match

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
import com.example.dev_bandung.footballmatchschedule.league.LeaguePresenter
import com.example.dev_bandung.footballmatchschedule.league.LeagueView
import com.example.dev_bandung.footballmatchschedule.model.League
import com.example.dev_bandung.footballmatchschedule.model.Match
import com.example.dev_bandung.footballmatchschedule.utils.invisible
import com.example.dev_bandung.footballmatchschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class LastMatch : Fragment(), MatchView, LeagueView {

    private var categories: MutableList<League> = mutableListOf()
    private var events: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var v: View
    private var idCategory : String = "0"
    private var temp : Int? = 3

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"
        fun newInstance(sectionNumber: Int): LastMatch {
            val fragment = LastMatch()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_last_match, container, false)
        temp = arguments?.getInt(ARG_SECTION_NUMBER)
        adapter = MatchAdapter(ctx, events, temp) {
            startActivity<DetailLastMatch>("id" to "${it.idEvent}")
        }
        v.listMatch.layoutManager = LinearLayoutManager(context)
        v.listMatch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        var leaguePresenter = LeaguePresenter(this,request, gson)
        leaguePresenter.getLeagueList()
        var presenter = MatchPresenter(this, request, gson)

        v.spnLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //toast(categories[position].idLeague.toString())
                idCategory = categories[position].idLeague.toString()
                when (temp) {
                    0 -> { presenter.getMatchList(idCategory) }
                    1 -> { presenter.getNextMatchList(idCategory) }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        v.swipeRefresh.onRefresh {
            when (temp) {
                0 -> { presenter.getMatchList(idCategory) }
                1 -> { presenter.getNextMatchList(idCategory) }
            }
        }
        return v
    }

    override fun showLeagueList(data: List<League>) {
        categories.clear()
        categories.addAll(data)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, data)
        v.spnLeague.adapter = spinnerAdapter
    }

    override fun hideLoading() {
        v.progressBar.invisible()
    }

    override fun showLoading() {
        v.progressBar.visible()
    }

    override fun showMatchList(data: List<Match>) {
        v.swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}