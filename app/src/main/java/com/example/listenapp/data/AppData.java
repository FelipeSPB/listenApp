package com.example.listenapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.listenapp.data.dao.AccessPlay;
import com.example.listenapp.model.Playlist;

@Database(entities = {Playlist.class}, version = 1, exportSchema = false)
public abstract class AppData extends RoomDatabase {
    private static final String DB_NAME = "playlists_db";
    private static AppData instance;



    public abstract AccessPlay accessPlay();
}