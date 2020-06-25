package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.GeoTopArtitstsResponse
import com.example.listenapp.model.apimodels.GeoTopTracksResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val LASTFM_APP_ID = "39b3d392674e04639c5e1ff6b7318df9"


interface ServiceLastFM {

    @GET("?method=geo.getTopArtists")
    suspend fun getGeoTop(
            @Query("api_key")
            apikey: String,
            @Query("country")
            country: String,
            @Query("format")
            format: String
    ): GeoTopArtitstsResponse

    @GET("?method=geo.gettoptracks")
    suspend fun getGeoTopTrack(
            @Query("api_key")
            apikey: String,
            @Query("page")
            page: Int = 1,
            @Query("country")
            country: String,
            @Query("format")
            format: String
    ): GeoTopTracksResponse




}
