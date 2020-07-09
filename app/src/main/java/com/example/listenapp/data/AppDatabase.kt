 package com.example.listenapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.listenapp.data.dao.AccessPlay
import com.example.listenapp.model.Playlist

@Database(entities = [Playlist::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accessPlay(): AccessPlay

}

object DatabaseBuilder {
    private var instance: AppDatabase? = null

    @JvmStatic
    fun getAppDatabase(context: Context) = instance ?: build(context)

    private fun build(context: Context): AppDatabase {
        val database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database"
        )
        database.allowMainThreadQueries()
        val appDatabase = database.build()
        instance = appDatabase
        return appDatabase
    }

    @JvmStatic
    fun destroyInstance() {
        instance = null
    }
}