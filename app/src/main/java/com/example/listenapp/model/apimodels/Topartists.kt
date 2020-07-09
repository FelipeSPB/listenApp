package com.example.listenapp.model.apimodels

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Topartists(
    @SerializedName("artist")
    val artist: MutableSet<Artist>,
    @SerializedName("@attr")
    val attr: Attr
) : Parcelable