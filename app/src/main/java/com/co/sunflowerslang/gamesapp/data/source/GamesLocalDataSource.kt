package com.co.sunflowerslang.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.data.PricesRange

interface GamesLocalDataSource : GamesDataSource {
    fun getGame(id: String): LiveData<Game>
    fun saveGames(games: List<Game>)
    fun getPopularGames(): LiveData<List<Game>>
    fun getNewGames(): LiveData<List<Game>>
    fun getAllGameNames(): LiveData<List<String>>
    fun getPricesRange(): LiveData<PricesRange>
    fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>>
}