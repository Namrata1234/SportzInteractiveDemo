package com.example.sportzinteractive.data.model

import java.io.Serializable

data class MatchDetail(
    val teamHome: String?=null,
    val teamAway: String?=null,
    val match: MatchInfo,
    val series: Series,
    val venue: Venue,
    val officials: Officials,
    val weather: String,
    val tossWonBy: String,
    val status: String,
    val playerMatch: String,
    val result: String
) :Serializable