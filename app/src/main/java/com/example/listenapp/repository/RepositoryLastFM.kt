package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.GeoTopArtitstsResponse
import retrofit.RetroInit

class RepositoryLastFM {
    private var url = "http://ws.audioscrobbler.com/2.0/"
    private var service = ServiceLastFM::class

    private val serviceLast = RetroInit(url).create(service)

    suspend fun getGeoTop(): GeoTopArtitstsResponse {
        return serviceLast.getGeoTop(LASTFM_APP_ID,"brazil","json")
    }

}