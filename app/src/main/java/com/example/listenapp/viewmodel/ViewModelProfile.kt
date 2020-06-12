package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.TextView

class ViewModelProfile(application: Application) : AndroidViewModel(application) {

    fun setUserData(textViewName: TextView, textViewQtdPasswords: TextView){
        textViewName.text = "Jalim Rabei"
        var countedPlaylists ="11"
        textViewQtdPasswords.text = countedPlaylists + " Playlists"
    }

}