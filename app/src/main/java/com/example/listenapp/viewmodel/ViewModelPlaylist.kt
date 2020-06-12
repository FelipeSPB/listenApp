package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.listenapp.model.Playlist
import com.example.listenapp.repository.RepositoryDatabase

class ViewModelPlaylist(application: Application) : AndroidViewModel(application) {
    //val characterResponse = MutableLiveData<>()
    //private val repositoryRickMorty = RepositoryRickMorty()

//    fun getCharacters(page: Int = 1) = CoroutineScope(IO).launch {
//        characterResponse.postValue(repositoryRickMorty.getCharacters(page))
//    }
    val  dataSet = MutableLiveData<MutableList<Playlist>>()
    private val context = getApplication<Application>().applicationContext
    private var repository = RepositoryDatabase(context)

    fun setPlaylists(name: String) {
        val playlist = Playlist()
        playlist.playlistName = name
        repository.getAccessPlay().insertAll(playlist)
        dataSet.postValue(repository.getAccessPlay().all)


    }




}