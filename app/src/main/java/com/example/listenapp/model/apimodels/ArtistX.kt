package com.example.listenapp.model.apimodels


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import custom.normalize

@SuppressLint("ParcelCreator")
@Parcelize
data class ArtistX(
    @SerializedName("name")
    val name: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("url")
    val url: String
) : Parcelable{
    fun formatName(): String {
        var formatString = name.replace(" ","-").replace("+","and").replace(".","-").replace("'","")
                .replace("/","-").toLowerCase().normalize()
        if (formatString.endsWith(".")||formatString.endsWith("-")){
           formatString = formatString.substring(0,formatString.length-1)
            println(formatString)
        }
        println(formatString)
        return formatString
    }
}