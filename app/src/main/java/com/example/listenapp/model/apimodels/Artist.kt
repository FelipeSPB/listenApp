package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import custom.normalize

@SuppressLint("ParcelCreator")
@Parcelize
data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("image")
    val image: List<Image>
) : Parcelable{
    fun formatName(): String {
        return name.replace(" ","-").replace("+","and").toLowerCase().normalize()
    }
}