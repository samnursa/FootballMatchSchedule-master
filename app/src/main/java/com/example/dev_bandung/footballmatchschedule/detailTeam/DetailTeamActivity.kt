package com.example.dev_bandung.footballmatchschedule.detailTeam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.main.PageTeamAdapter
import com.example.dev_bandung.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var team: Team
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent: Intent = getIntent()
        team = intent.getParcelableExtra("team")

        Picasso.get().load(team.strTeamBadge).into(imgTeam)
        txtTeamName.text = team.strTeam
        txtFormedYear.text = team.intFormedYear
        txtStadiumName.text = team.strStadium

        viewPager = viewpager_team
        tabs = tabs_team

        val fragmentAdapter = PageTeamAdapter(supportFragmentManager, team.strDescriptionEN, team.idTeam)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
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
