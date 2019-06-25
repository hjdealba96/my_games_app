package com.co.gamesapp

import android.app.Application
import com.co.gamesapp.data.source.local.GamesRoomDatabase

class GamesApplication : Application() {
    companion object {
        lateinit var roomDatabase: GamesRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase = GamesRoomDatabase.getDatabase(this)
    }
}