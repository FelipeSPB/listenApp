package com.example.listenapp.repository

import android.content.Context
import com.example.listenapp.data.DatabaseBuilder
import com.example.listenapp.data.dao.AccessPlay



class RepositoryDatabase(context: Context) {
    private var database = DatabaseBuilder.getAppDatabase(context)
    private var accessPlay = database.accessPlay()

    fun getAccessPlay(): AccessPlay {
        return accessPlay
    }
}