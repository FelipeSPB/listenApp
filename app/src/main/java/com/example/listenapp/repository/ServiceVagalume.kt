package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.HotNewsResponse
import com.example.listenapp.model.apimodels.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val APP_ID = "a902d179048ebd77b97fbd136899e443"
//const val APP_ID = "660a4395f992ff67786584e238f501aa"

interface ServiceVagalume {

    @GET("hotspots.php?")
    suspend fun getHotSpot(
            @Query("apikey")
            apikey: String
    ): HotNewsResponse

    @GET("news/index.js")
    suspend fun getNews(): NewsResponse

}
