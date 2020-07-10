package com.example.listenapp.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.repository.RepositoryDatabase
import com.example.listenapp.repository.RepositoryLastFM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception



class ViewModelMusic(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val tracks = MutableLiveData<MutableSet<Track>>()
    private val repositoryFM =  RepositoryLastFM()
    val repository = RepositoryDatabase(context)


    fun getTopTracks(page: Int= 1) = CoroutineScope(IO).launch {
        try {
            tracks.postValue(repositoryFM.getTrackTop(page).tracks.track)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }
}