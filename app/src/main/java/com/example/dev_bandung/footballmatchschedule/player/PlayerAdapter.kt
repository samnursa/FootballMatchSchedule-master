package com.example.dev_bandung.footballmatchschedule.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_list.view.*

class PlayerAdapter(private val context: Context, private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_list, parent, false))

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val mPlayerImage = view.imgPlayer
        private val mPlayerName = view.txtPlayerName
        private val mPlayerPosition = view.txtPosition

        fun bindItem(players: Player, listener: (Player) -> Unit) {
            Picasso.get().load(players.strCutout).into(mPlayerImage)
            mPlayerName.text = players.strPlayer
            mPlayerPosition.text = players.strPosition
            itemView.setOnClickListener { listener(players) }
        }
    }
}