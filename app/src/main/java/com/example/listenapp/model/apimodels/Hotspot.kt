package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Hotspot(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("artUrl")
    val artUrl: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_fmt")
    val dateFmt: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("descr")
    val descr: String,
    @SerializedName("pic_src")
    val picSrc: String,
    @SerializedName("art_pic_src")
    val artPicSrc: String,
    @SerializedName("pic_width")
    val picWidth: String,
    @SerializedName("pic_height")
    val picHeight: String,
    @SerializedName("colors")
    val colors: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("musicID")
    val musicID: String,
    @SerializedName("musTitle")
    val musTitle: String,
    @SerializedName("musUrl")
    val musUrl: String
) : Parcelable