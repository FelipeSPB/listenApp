package com.example.listenapp.repository

import com.example.listenapp.model.apimodels.ArtVagalume.ArtistResponse
import com.example.listenapp.model.apimodels.HotNewsResponse
import com.example.listenapp.model.apimodels.NewsResponse
import retrofit.RetroInit

class RepositoryVagalume {
    private var url = "https://api.vagalume.com.br/"
    private var url2 = "https://www.vagalume.com.br/"
    private var service = ServiceVagalume::class

    private val serviceVagalume = RetroInit(url).create(service)
    private val serviceNews = RetroInit(url2).create(service)

    suspend fun getHotSpot(): HotNewsResponse {
        return serviceVagalume.getHotSpot(APP_ID)
    }

    suspend fun getNews(): NewsResponse {
        return serviceNews.getNews()
    }
}