package com.example.listenapp.model

import android.arch.persistence.room.*
import android.os.Parcelable
import com.example.listenapp.R
import com.example.listenapp.custom.toJsonArray
import com.example.listenapp.custom.toObjekts
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "playlists")
data class Playlist(

        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "playlist_name")
        var playlistName: String = "",

        @ColumnInfo(name = "total_music")
        var quantity: Int = 0,

        @ColumnInfo(name = "music_list")
        var musics:  String = "",

        @ColumnInfo(name = "playlist_image")
        var playlistImage: Int = R.drawable.ic_vinyl_record

) : Parcelable {
    @Ignore
    var musicList:ArrayList<Music> = ArrayList()

    fun setList(){
        musics = musicList.toJsonArray()
    }

    fun getList(){
        musicList.clear()
        musicList.addAll(musics.toObjekts())
    }

    fun addToPlaylist(music: Music){
        musicList.add(music)
        setList()
    }

}