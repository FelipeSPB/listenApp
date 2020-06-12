package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.listenapp.repository.RepositoryVagalume

class ViewModelNews(application: Application) : AndroidViewModel(application){

    val NewsSet = MutableLiveData<String>()
    private val repository =  RepositoryVagalume()

    fun getWeather(lat: String, lon: String) = CoroutineScope(IO).launch {
        repoWeather.getWeather(lat, lon).let { resposta ->
            stringFormatada.postValue(
                    "País: " + resposta.sys?.country + "\n" +
                            "Temp: " + resposta.main?.temp + "\n" +
                            "Temp(Min): " + resposta.main?.temp_min + "\n" +
                            "Temp(Max): " + resposta.main?.temp_max + "\n" +
                            "Humildade: " + resposta.main?.humidity + "\n" +
                            "Pressão: " + resposta.main?.pressure
            )
        }
    }

}