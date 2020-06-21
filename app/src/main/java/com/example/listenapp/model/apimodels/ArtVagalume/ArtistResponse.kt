package com.example.listenapp.model.apimodels.ArtVagalume


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ArtistResponse(
    @SerializedName("artist")
    val artist: Artist
) : Parcelable