package com.example.dev_bandung.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
        @SerializedName("idLeague")
        var idLeague: String? = null,

        @SerializedName("strLeague")
        var strLeague: String? = null
) : Parcelable {
        override fun toString(): String {
                return strLeague ?: "Category"
        }
}