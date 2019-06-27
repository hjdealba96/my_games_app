package com.co.gamesapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.source.local.games.GamesDao

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GamesRoomDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao

    companion object {

        private const val DATABASE_NAME = "games_database"

        @Volatile
        private var INSTANCE: GamesRoomDatabase? = null

        fun getDatabase(context: Context): GamesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}