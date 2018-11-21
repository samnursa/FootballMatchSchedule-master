package com.example.dev_bandung.footballmatchschedule.db

data class Favorite(
        val idEvent: Long?,
        val homeTeam: String?,
        val awayTeam: String?,
        val homeScore: String?,
        val awayScore: String?,
        val dateEvent: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_EVENT: String = "ID_"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
    }
}