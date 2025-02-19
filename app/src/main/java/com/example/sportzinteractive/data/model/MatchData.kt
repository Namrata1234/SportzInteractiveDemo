package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchData(
    @SerializedName("Matchdetail")
    val matchDetail: MatchDetail,
    val nuggets: List<String>,
    val innings: List<Inning>,
    val teams: Map<String, Team>
) : Serializable
