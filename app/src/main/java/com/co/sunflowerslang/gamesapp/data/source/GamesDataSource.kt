package com.co.sunflowerslang.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.sunflowerslang.gamesapp.data.Game

interface GamesDataSource {
    fun getAllGames(): LiveData<List<Game>>
}