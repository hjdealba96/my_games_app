package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.gamesapp.data.Game

interface GamesDataSource {
    fun getAllGames(): LiveData<List<Game>>
}