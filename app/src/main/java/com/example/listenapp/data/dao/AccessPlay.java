package com.example.listenapp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.listenapp.model.Playlist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface AccessPlay {
    @Query("SELECT * FROM playlists")
    List<Playlist> getAll();

    @Query("SELECT * FROM playlists WHERE playlist_name LIKE :name")
    Playlist findByName(String name);

    @Insert
    void insertAll(Playlist... playlists);

    @Delete
    void delete(Playlist playlist);

    @Update
    void update(Playlist playlist);

}
