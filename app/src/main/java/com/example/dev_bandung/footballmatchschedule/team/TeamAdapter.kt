package com.example.dev_bandung.footballmatchschedule.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_list.view.*

class TeamAdapter(private val context: Context, private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_list, parent, false))

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mTeamBadge = view.imgTeamBadge
        private val mTeamName = view.txtTeamName

        fun bindItem(teams: Team, listener: (Team) -> Unit) {
            Picasso.get().load(teams.strTeamBadge).into(mTeamBadge)
            mTeamName.text = teams.strTeam
            itemView.setOnClickListener { listener(teams) }
        }
    }
}