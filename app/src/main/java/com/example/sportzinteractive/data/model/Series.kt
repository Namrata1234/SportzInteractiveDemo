package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Status")
    val status: String
)