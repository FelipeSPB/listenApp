package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.HotNewsResponse
import retrofit.RetroInit

class RepositoryVagalume {
    private var url = "api.vagalume.com.br/"
    private var service = ServiceHotSpot::class

    private val serviceHotSpot = RetroInit(url).create(service)

    suspend fun getHotSpot(): HotNewsResponse {
        return serviceHotSpot.getHotSpot(APP_ID)
    }
}