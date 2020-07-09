package com.example.listenapp.model.apimodels.ArtVagalume


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Genre(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable