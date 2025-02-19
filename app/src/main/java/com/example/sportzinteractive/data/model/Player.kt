package com.example.sportzinteractive.data.model

data class Player(
    val position: String,
    val nameFull: String,
    val isCaptain: Boolean?,
    val isKeeper: Boolean?,
    val batting: Batting,
    val bowling: Bowling
)