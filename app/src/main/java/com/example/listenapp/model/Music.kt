package com.example.listenapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Music(
        var musicName: String,
        var id:Int,
        var image:String,
        var url:String
) : Parcelable {

}