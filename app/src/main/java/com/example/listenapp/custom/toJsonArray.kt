package com.example.listenapp.custom

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun <T : Parcelable> Collection<T>.toJsonArray(): String {
    val string = StringBuilder()
    this.forEachIndexed { index, objekt ->
        string.append(
                if (size == 1){
                    "[${objekt.toJson}]"
                }
                else{
                    when (index) {
                        0 -> "[${objekt.toJson},"
                        size - 1 -> "${objekt.toJson}]"
                        else -> "${objekt.toJson},"
                    }
                }
        )

    }
    return string.toString()
}

inline fun <reified T : Parcelable>
        T.fromJson(json: String): T = Gson().fromJson(json, T::class.java)

inline fun <reified T> String.toObjekt(): T =
        Gson().fromJson(this, T::class.java)

inline fun <reified T> String.toObjekts(): T =
        Gson().fromJson(this, object : TypeToken<T>() {}.type)

val <T : Parcelable>
        T.toJson
    get(): String = GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this)