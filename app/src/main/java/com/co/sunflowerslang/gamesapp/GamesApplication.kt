package com.co.sunflowerslang.gamesapp

import android.app.Application
import com.co.sunflowerslang.gamesapp.data.source.local.GamesRoomDatabase

class GamesApplication : Application() {
    companion object {
        lateinit var roomDatabase: GamesRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase = GamesRoomDatabase.getDatabase(this)
    }
}