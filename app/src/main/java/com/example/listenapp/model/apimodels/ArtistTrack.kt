package com.example.listenapp.model.apimodels

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ArtistTrack (
    @SerializedName("name")
    val name: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("url")
    val url: String
): Parcelable