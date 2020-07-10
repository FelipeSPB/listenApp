package com.example.listenapp.viewmodel



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listenapp.model.apimodels.Artist
import com.example.listenapp.model.apimodels.News
import com.example.listenapp.repository.RepositoryLastFM
import com.example.listenapp.repository.RepositoryVagalume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception



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
        try {
            newsSet.postValue(repositoryFM.getGeoTop().topartists.artist)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: "+backendException.message)
        }
    }




}