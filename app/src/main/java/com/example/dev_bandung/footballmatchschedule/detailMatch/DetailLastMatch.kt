package com.example.dev_bandung.footballmatchschedule.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.dev_bandung.footballmatchschedule.R
import com.example.dev_bandung.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.dev_bandung.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.dev_bandung.footballmatchschedule.R.id.*
import com.example.dev_bandung.footballmatchschedule.R.menu.detail_menu
import com.example.dev_bandung.footballmatchschedule.api.ApiRepository
import com.example.dev_bandung.footballmatchschedule.db.Favorite
import com.example.dev_bandung.footballmatchschedule.db.database
import com.example.dev_bandung.footballmatchschedule.model.Match
import com.example.dev_bandung.footballmatchschedule.model.Team
import com.example.dev_bandung.footballmatchschedule.utils.invisible
import com.example.dev_bandung.footballmatchschedule.utils.visible
import org.jetbrains.anko.design.snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_last_match.*
import org.jetbrains.anko.db.*

class DetailLastMatch : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var match: Match
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_last_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = getIntent()
        id = intent.getStringExtra("id")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailMatch(id)
    }

    override fun hideLoading(){
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showDetailMatch(data: List<Match>) {
        match = Match(
                data[0].idEvent,
                data[0].strHomeTeam,
                data[0].strAwayTeam,
                data[0].strHomeScore,
                data[0].strAwayScore,
                data[0].dateEvent,
                data[0].strHomeGoalDetails,
                data[0].strAwayGoalDetails,
                data[0].intHomeShots,
                data[0].intAwayShots,
                data[0].strHomeLineupGoalkeeper,
                data[0].strHomeLineupDefense,
                data[0].strHomeLineupMidfield,
                data[0].strHomeLineupForward,
                data[0].strHomeLineupSubstitutes,
                data[0].strAwayLineupGoalkeeper,
                data[0].strAwayLineupDefense,
                data[0].strAwayLineupMidfield,
                data[0].strAwayLineupForward,
                data[0].strAwayLineupSubstitutes
                )

        txtDate.text = match.dateEvent

        txtTeamHome.text = match.strHomeTeam
        txtScoreHome.text = match.strHomeScore
        txtGoalsHome.text = match.strHomeGoalDetails
        txtShotsHome.text = match.intHomeShots
        txtHomeGoalKeeper.text = match.strHomeLineupGoalkeeper
        txtHomeDefense.text = match.strHomeLineupDefense
        txtHomeMidfield.text = match.strHomeLineupMidfield
        txtHomeForward.text = match.strHomeLineupForward
        txtHomeSubtitutes.text = match.strHomeLineupSubstitutes

        txtTeamAway.text = match.strAwayTeam
        txtScoreAway.text = match.strAwayScore
        txtGoalsAway.text = match.strAwayGoalDetails
        txtShotsAway.text = match.intAwayShots
        txtAwayGoalKeeper.text = match.strAwayLineupGoalkeeper
        txtAwayDefense.text = match.strAwayLineupDefense
        txtAwayMidfield.text = match.strAwayLineupMidfield
        txtAwayForward.text = match.strAwayLineupForward
        txtAwaySubtitutes.text = match.strAwayLineupSubstitutes

        presenter.getTeamHome(match.strHomeTeam)
        presenter.getTeamAway(match.strAwayTeam)
    }

    override fun showImageHome(data: List<Team>) {
        val image = data.map { it.strTeamBadge
        }
        Picasso.get().load(image[0]).into(imageHome)
    }

    override fun showImageAway(data: List<Team>) {
        val image = data.map { it.strTeamBadge
        }
        Picasso.get().load(image[0]).into(imageAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_ = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to match.idEvent,
                        Favorite.HOME_TEAM to match.strHomeTeam,
                        Favorite.AWAY_TEAM to match.strAwayTeam,
                        Favorite.HOME_SCORE to match.strHomeScore,
                        Favorite.AWAY_SCORE to match.strAwayScore,
                        Favorite.DATE_EVENT to match.dateEvent)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_ = {id})", "id" to id)
            }
            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
