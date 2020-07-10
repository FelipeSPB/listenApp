package com.example.listenapp.data.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listenapp.model.Playlist;
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
