package com.example.listenapp.model.apimodels.ArtVagalume


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Rank(
    @SerializedName("pos")
    val pos: String,
    @SerializedName("period")
    val period: Int,
    @SerializedName("views")
    val views: String,
    @SerializedName("uniques")
    val uniques: String,
    @SerializedName("points")
    val points: String
) : Parcelable