package com.example.listenapp.model.apimodels

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Streamable (
        @SerializedName("#text")
        val text: String,
        @SerializedName("fulltrack")
        val fulltrack: String
): Parcelable