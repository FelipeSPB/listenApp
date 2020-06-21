package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class News(
    @SerializedName("id")
    val id: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("kicker")
    val kicker: String,
    @SerializedName("featured")
    val featured: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("inserted")
    val inserted: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("pic_src")
    val picSrc: String,
    @SerializedName("pic_width")
    val picWidth: String,
    @SerializedName("pic_height")
    val picHeight: String,
    @SerializedName("pic_caption")
    val picCaption: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("tags")
    val tags: List<String>
) : Parcelable{
    fun formatImage(): String {
        return "http://www.vagalume.com.br$picSrc"
    }
}