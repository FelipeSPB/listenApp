package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Streamable(
    @SerializedName("#text")
    val text: String,
    @SerializedName("fulltrack")
    val fulltrack: String
) : Parcelable