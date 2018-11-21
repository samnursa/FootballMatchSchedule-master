package com.example.dev_bandung.footballmatchschedule.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.db.database
import com.example.dev_bandung.footballmatchschedule.detailMatch.DetailLastMatch
import com.example.dev_bandung.footballmatchschedule.utils.invisible
import com.example.dev_bandung.footballmatchschedule.utils.visible
import com.example.dev_bandung.footballmatchschedule.db.Favorite
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment(), FavoriteView {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favorite, container, false)
        adapter = FavoriteAdapter(ctx, favorites) {
            startActivity<DetailLastMatch>("id" to "${it.idEvent}")
        }
        v.listMatch.layoutManager = LinearLayoutManager(context)
        v.listMatch.adapter = adapter
        showFavoriteList()
        hideLoading()
        v.swipeRefresh.onRefresh {
            favorites.clear()
            showFavoriteList()
        }
        return v
    }

    override fun hideLoading() {
        v.progressBar.invisible()
    }

    override fun showLoading() {
        v.progressBar.visible()
    }

    override fun showFavoriteList() {
        context?.database?.use {
            v.swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
