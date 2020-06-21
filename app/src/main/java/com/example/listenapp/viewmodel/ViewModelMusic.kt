package com.example.listenapp.viewmodel


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.example.listenapp.model.apimodels.Artist
import com.example.listenapp.model.apimodels.HotNewsResponse
import com.example.listenapp.model.apimodels.Hotspot
import com.example.listenapp.model.apimodels.News
import com.example.listenapp.repository.RepositoryLastFM
import com.example.listenapp.repository.RepositoryVagalume
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths


class ViewModelMusic: ViewModel() {

    val artists = MutableLiveData<MutableSet<Artist>>()
    private val repositoryFM =  RepositoryLastFM()



    fun  getArtTop() = CoroutineScope(IO).launch {
        try {
            artists.postValue(repositoryFM.getGeoTop().topartists.artist)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }





}