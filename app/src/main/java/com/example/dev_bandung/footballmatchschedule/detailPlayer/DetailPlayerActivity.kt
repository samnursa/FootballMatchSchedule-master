package com.example.dev_bandung.footballmatchschedule.detailPlayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent: Intent = getIntent()
        player = intent.getParcelableExtra("player")
        Picasso.get().load(player.strFanart1).into(imgFanArt)
        txtWeight.text = player.strWeight
        txtHeight.text = player.strHeight
        txtPosition.text = player.strPosition
        txtDescPlayer.text = player.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
