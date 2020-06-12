package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.listenapp.repository.RepositoryVagalume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ViewModelNews(application: Application) : AndroidViewModel(application){

    val NewsSet = MutableLiveData<String>()
    private val repository =  RepositoryVagalume()


}