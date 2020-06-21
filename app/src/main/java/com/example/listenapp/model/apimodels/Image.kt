package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Image(
    @SerializedName("#text")
    val url: String,
    @SerializedName("size")
    val size: String
) : Parcelable