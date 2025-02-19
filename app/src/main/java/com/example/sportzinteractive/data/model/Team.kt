package com.example.sportzinteractive.data.model

data class Team(
    val nameFull: String,
    val nameShort: String,
    val players: Map<String, Player>
)