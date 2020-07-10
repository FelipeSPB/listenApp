package com.example.listenapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.listenapp.model.Playlist
import com.example.listenapp.repository.RepositoryDatabase

class ViewModelPlaylist(application: Application) : AndroidViewModel(application) {

    val dataSet = MutableLiveData<MutableList<Playlist>>()
    private val context = getApplication<Application>().applicationContext
    private var repository = RepositoryDatabase(context)

    fun setPlaylists(name: String) {
        val playlist = Playlist()
        playlist.playlistName = name
        repository.getAccessPlay().insertAll(playlist)
        dataSet.postValue(repository.getAccessPlay().all)
    }

    fun getPlaylists() {
        dataSet.postValue(repository.getAccessPlay().all)
    }

}