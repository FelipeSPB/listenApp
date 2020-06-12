package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class HotNewsResponse(
    @SerializedName("hotspots")
    val hotspots: List<Hotspot>
) : Parcelable