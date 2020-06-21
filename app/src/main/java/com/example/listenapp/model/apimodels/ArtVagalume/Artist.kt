package com.example.listenapp.model.apimodels.ArtVagalume


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Artist(
    @SerializedName("id")
    val id: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("pic_small")
    val picSmall: String,
    @SerializedName("pic_medium")
    val picMedium: String,
    @SerializedName("rank")
    val rank: Rank,
    @SerializedName("genre")
    val genre: List<Genre>,
    @SerializedName("related")
    val related: List<Related>,
    @SerializedName("toplyrics")
    val toplyrics: Toplyrics,
    @SerializedName("lyrics")
    val lyrics: Lyrics,
    @SerializedName("albums")
    val albums: Albums
) : Parcelable