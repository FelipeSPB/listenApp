package com.example.listenapp.model.apimodels.ArtVagalume


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("url")
    val url: String
) : Parcelable