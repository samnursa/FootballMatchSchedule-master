package com.example.dev_bandung.footballmatchschedule.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.db.Favorite
import kotlinx.android.synthetic.main.match_list.view.*

class FavoriteAdapter(private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list, parent, false))

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mTeamHome = view.txtTeamHome
        private val mTeamAway = view.txtTeamAway
        private val mScoreHome = view.txtScoreHome
        private val mScoreAway = view.txtScoreAway
        private val mDate = view.txtDate

        fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
            mTeamHome.text = favorite.homeTeam
            mTeamAway.text = favorite.awayTeam
            mScoreHome.text = favorite.homeScore
            mScoreAway.text = favorite.awayScore
            mDate.text = favorite.dateEvent
            itemView.setOnClickListener { listener(favorite) }
        }
    }
}