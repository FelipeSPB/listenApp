package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Tracks(
    @SerializedName("track")
    val track: MutableSet<Track>,
    @SerializedName("@attr")
    val attr: AttrXX
) : Parcelable