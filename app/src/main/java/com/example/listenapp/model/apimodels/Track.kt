package com.example.listenapp.model.apimodels

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import custom.normalize

@SuppressLint("ParcelCreator")
@Parcelize
data class Track(
        @SerializedName("name")
        val name: String,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("playcount")
        val listeners: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("streamable")
        val streamable: Streamable,
        @SerializedName("artist")
        val artist: ArtistTrack,
        @SerializedName("image")
        val image: List<Image>,
        @SerializedName("@attr")
        val attr : AttrRank
) : Parcelable{
    fun formatName(): String {
        return name.replace(" ","-").replace("+","and").toLowerCase().normalize()
    }
}