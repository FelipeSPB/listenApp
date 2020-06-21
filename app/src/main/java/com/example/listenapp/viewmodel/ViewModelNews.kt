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


class ViewModelNews: ViewModel() {

    val newsSet = MutableLiveData<MutableSet<Artist>>()
    val allNewsSet = MutableLiveData<MutableSet<News>>()
    private val repository =  RepositoryVagalume()
    private val repositoryFM =  RepositoryLastFM()

    /*fun hotSpot() = CoroutineScope(IO).launch {
        try {
            var request = repository.getHotSpot()
            newsSet.postValue(request.hotspots)
        } catch (backendException: Exception) {
          Log.i("LOG", "Erro: "+backendException.message)
        }
    }*/

    fun news() = CoroutineScope(IO).launch {
        try {
            allNewsSet.postValue(repository.getNews().news)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }

    fun hotSpot() = CoroutineScope(IO).launch {
        newsSet.postValue(repositoryFM.getGeoTop().topartists.artist)
    }




}