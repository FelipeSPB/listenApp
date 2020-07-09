package com.example.listenapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.ImageView
import android.widget.TextView
import com.example.listenapp.R

class ViewModelProfile(application: Application) : AndroidViewModel(application) {


    fun setUserData(userProfile: ImageView, textViewName: TextView, textViewQtdPasswords: TextView) {
        userProfile.setImageResource(R.drawable.saitama)
        textViewName.text = "Saitama"
        var countedPlaylists = "11"
        textViewQtdPasswords.text = countedPlaylists + " Playlists"
    }

}
