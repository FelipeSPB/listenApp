package dbroomkt

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.example.listenapp.R
import com.example.listenapp.model.Music

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "playlists")
data class EntityPlaylist(

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,

    @ColumnInfo(name = "playlist_name")
    var playlistName: String = "",

    @ColumnInfo(name = "total_music")
    var quantity: Int = 0,

    @ColumnInfo(name = "playlist_image")
    var playlistImage: Int = R.drawable.ic_vinyl_record,

    @ColumnInfo(name = "collection")
    var collection: String = ""
)

@Parcelize
data class ParcelPlaylist(
    @SerializedName("uid") var uid: Int = 0,
    @SerializedName("playlist_name") var playlistName: String = "",
    @SerializedName("total_music") var quantity: Int = 0,
    @SerializedName("playlist_image") var  playlistImage: Int = R.drawable.ic_vinyl_record,
    @Suppress("ArrayInDataClass") @SerializedName("collection")
    var collection: ArrayList<Music> = arrayListOf()   //  list, array, set
    //var collection: Map<Int, Response> = mapOf(0 to Response("Z"))  //  map

) : Parcelable



fun <T> T.toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)

val <T : Parcelable> Array<T>.toJson get(): String = toJson()

val <T : Map<*, Parcelable>> T.toJson get(): String = toJson()

val <T : Iterable<Parcelable>> T.toJson get(): String = toJson()

inline fun <reified T> String.toObjekt(): T =
    Gson().fromJson(this, T::class.java)

inline fun <reified T> String.toObjekts(): T =
    Gson().fromJson(this, object : TypeToken<T>() {}.type)