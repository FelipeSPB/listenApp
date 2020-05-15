package com.example.listenapp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.listenapp.model.Playlist;

import java.util.List;

@Dao
public interface AccessPlay {
    @Query("SELECT * FROM playlists")
    List<Playlist> getAll();

    @Query("SELECT * FROM playlists WHERE uid IN (:Ids)")
    List<Playlist> loadAllByIds(int[] Ids);

    @Query("SELECT * FROM playlists WHERE playlist_name LIKE :name AND " +
            "total_music LIKE :quantity AND " + "playlist_image LIKE :quantity")
    Playlist findByName(String name, int quantity, int image);

    @Insert
    void insertAll(Playlist... playlists);

    @Delete
    void delete(Playlist playlist);

}
