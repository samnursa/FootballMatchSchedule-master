package com.example.dev_bandung.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,
        @SerializedName("strPlayer")
        var strPlayer: String? = null,
        @SerializedName("strPosition")
        var strPosition: String? = null,
        @SerializedName("strCutout")
        var strCutout: String? = null,
        @SerializedName("strFanart1")
        var strFanart1: String? = null,
        @SerializedName("strHeight")
        var strHeight: String? = null,
        @SerializedName("strWeight")
        var strWeight: String? = null,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null
) : Parcelable