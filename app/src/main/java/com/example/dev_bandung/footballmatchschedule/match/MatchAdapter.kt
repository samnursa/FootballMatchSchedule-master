package com.example.dev_bandung.footballmatchschedule.match

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.model.Match
import kotlinx.android.synthetic.main.match_list.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MatchAdapter(private val context: Context, private val events: List<Match>, private val temp: Int?, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list, parent, false))

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(events[position], temp, context, listener)
    }

    override fun getItemCount(): Int = events.size

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mTeamHome = view.txtTeamHome
        private val mTeamAway = view.txtTeamAway
        private val mScoreHome = view.txtScoreHome
        private val mScoreAway = view.txtScoreAway
        private val mDate = view.txtDate
        private val imgAlarm = view.imgAlarm

        fun bindItem(events: Match, temp: Int?, context: Context , listener: (Match) -> Unit) {
            if(temp == 1){
                imgAlarm.visibility = View.VISIBLE
            }else{
                imgAlarm.visibility = View.INVISIBLE
            }

            imgAlarm.onClick {
                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra(CalendarContract.Events.TITLE, events.strEvent)
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, events.dateEvent)
                intent.putExtra(CalendarContract.Events.ALL_DAY, false)
                context.startActivity(intent)
            }

            mTeamHome.text = events.strHomeTeam
            mTeamAway.text = events.strAwayTeam
            mScoreHome.text = events.strHomeScore
            mScoreAway.text = events.strAwayScore
            mDate.text = events.dateEvent
            itemView.setOnClickListener { listener(events) }
        }
    }
}