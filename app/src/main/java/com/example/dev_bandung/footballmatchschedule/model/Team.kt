package com.example.dev_bandung.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        @SerializedName("idTeam")
        var idTeam: String? = null,
        @SerializedName("strTeam")
        var strTeam: String? = null,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null,
        @SerializedName("intFormedYear")
        var intFormedYear: String? = null,
        @SerializedName("strStadium")
        var strStadium: String? = null,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null
) : Parcelable