package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.HotNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val APP_ID = "a902d179048ebd77b97fbd136899e443"

interface ServiceHotSpot {

    @GET("hotspots.php?")
    suspend fun getHotSpot(
            @Query("apikey")
            apikey: String
    ): HotNewsResponse

}
