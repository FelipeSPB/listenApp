package com.example.listenapp.viewmodel


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.listenapp.model.apimodels.Artist
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.repository.RepositoryLastFM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception


class ViewModelMusic: ViewModel() {

    //val artists = MutableLiveData<MutableSet<Artist>>()
    val tracks = MutableLiveData<MutableSet<Track>>()
    private val repositoryFM =  RepositoryLastFM()

    fun  getTopTracks(page: Int= 1) = CoroutineScope(IO).launch {
        try {
            tracks.postValue(repositoryFM.getTrackTop(page).tracks.track)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }

    /*fun  getArtTop() = CoroutineScope(IO).launch {
        try {
            artists.postValue(repositoryFM.getGeoTop().topartists.artist)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }*/





}