package com.example.sportzinteractive.data.remote

import com.example.sportzinteractive.data.model.MatchData
import retrofit2.http.GET

interface APIService{

    @GET("nzin01312019187360.json")
    suspend fun getMatchDetails(): MatchData
}