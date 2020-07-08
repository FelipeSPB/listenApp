package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.listenapp.R
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.repository.RepositoryLastFM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random.Default.nextInt

class ViewModelProfile(application: Application) : AndroidViewModel(application) {

    val tracks = MutableLiveData<MutableSet<Track>>()
    private val repositoryFM = RepositoryLastFM()

    fun getTopTrack() = CoroutineScope(IO).launch {
        try {
            tracks.postValue(repositoryFM.getGeoTopTracks().toptracks.track)
        } catch (backendException: Exception) {
            Log.i("LOG", "Erro: " + backendException.message)
        }
    }


    fun setUserData(userProfile: ImageView, textViewName: TextView, textViewQtdPasswords: TextView) {
        userProfile.setImageResource(R.drawable.saitama)
        textViewName.text = "Saitama"
        var countedPlaylists = "11"
        textViewQtdPasswords.text = countedPlaylists + " Playlists"
    }

}
