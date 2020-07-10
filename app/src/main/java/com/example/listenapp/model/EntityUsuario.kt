package com.example.listenapp.model
//
//
//import android.os.Parcelable
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.google.gson.annotations.SerializedName
//import com.google.gson.reflect.TypeToken
//import kotlinx.android.parcel.Parcelize
//
//@Entity(tableName = "usuario")
//data class EntityUsuario(
//
//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0,
//
//    @ColumnInfo(name = "first_name")
//    var nome: String = "",
//
//    @ColumnInfo(name = "last_name")
//    var sobrenome: String = "",
//
//    @ColumnInfo
//    var idade: Int = 0,
//
//    @ColumnInfo
//    var collection: String = ""
//)
//
//@Parcelize
//data class ParcelUsuario(
//    @SerializedName("id") var id: Int = 0,
//    @SerializedName("nome") var nome: String = "",
//    @SerializedName("sobrenome") var sobrenome: String = "",
//    @SerializedName("idade") var idade: Int = 0,
//    @Suppress("ArrayInDataClass") @SerializedName("collection")
//    var collection: Map<Int, Response> = mapOf(0 to Response("Z"))  //  map
////    var collection: Array<Response> = arrayOf(Response("Z"))   //  list, array, set
//) : Parcelable
//
//@Parcelize
//data class Response(val whiskas: String) : Parcelable
//
//fun main() {
//
////    val response = ParcelUsuario(  // list, array, set
////        collection = arrayOf(
////            Response("w"),
////            Response("h"),
////            Response("i"),
////            Response("s"),
////            Response("k"),
////            Response("a"),
////            Response("s")
////        )
////    )
//
//    val response = ParcelUsuario(   //  map
//        collection = mapOf(
//            11 to Response("w"),
//            22 to Response("h"),
//            33 to Response("i"),
//            44 to Response("s"),
//            55 to Response("k"),
//            66 to Response("a"),
//            77 to Response("s")
//        )
//    )
//
//    val usuarioEntity = EntityUsuario(
//        response.id,
//        response.nome,
//        response.sobrenome,
//        response.idade,
//            //com.example.listenapp.model.toJson
//    )
//
//    val usuarioParcel = ParcelUsuario(
//        usuarioEntity.id,
//        usuarioEntity.nome,
//        usuarioEntity.sobrenome,
//        usuarioEntity.idade,
//            //com.example.listenapp.model.toObjekts()
//    )
//
////    usuarioParcel.collection.forEach { println(it.whiskas) }  // list, array, set
//    usuarioParcel.collection.forEach { println(it.value.whiskas) }  // map
//}
//
//fun <T> T.toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
//
//val <T : Parcelable> Array<T>.toJson get(): String = com.example.listenapp.model.toJson()
//
//val <T : Map<*, Parcelable>> T.toJson get(): String = com.example.listenapp.model.toJson()
//
//val <T : Iterable<Parcelable>> T.toJson get(): String = com.example.listenapp.model.toJson()
//
//inline fun <reified T> String.toObjekt(): T =
//    Gson().fromJson(this, T::class.java)
//
//inline fun <reified T> String.toObjekts(): T =
//    Gson().fromJson(this, object : TypeToken<T>() {}.type)