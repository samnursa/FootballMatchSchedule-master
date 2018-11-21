package com.example.dev_bandung.footballmatchschedule.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.detailPlayer.DetailPlayerActivity
import com.example.dev_bandung.footballmatchschedule.model.Player
import com.example.dev_bandung.footballmatchschedule.utils.invisible
import com.example.dev_bandung.footballmatchschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment : Fragment(), PlayerView {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var adapter: PlayerAdapter
    private lateinit var v: View
    private var id : String? = "default"

    companion object {
        private val ARG_SECTION_NUMBER = "section_param"
        fun newInstance(sectionParam: String?): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putString(ARG_SECTION_NUMBER, sectionParam)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_player, container, false)
        id = arguments?.getString(ARG_SECTION_NUMBER)
        adapter = PlayerAdapter(ctx, players){
            startActivity<DetailPlayerActivity>("player" to it)
        }
        v.listPlayer.layoutManager = LinearLayoutManager(context)
        v.listPlayer.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        var playerPresenter = PlayerPresenter(this,request, gson)
        playerPresenter.getPlayerList(id)
        return v
    }

    override fun showLoading() {
        v.progressBarPlayer.visible()
    }

    override fun hideLoading() {
        v.progressBarPlayer.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}