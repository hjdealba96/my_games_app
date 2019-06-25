package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.PricesRange

interface GamesLocalDataSource : GamesDataSource {
    fun saveGames(games: List<Game>)
    fun getPopularGames(): LiveData<List<Game>>
    fun getNewGames(): LiveData<List<Game>>
    fun getAllGameNames(): LiveData<List<String>>
    fun getPricesRange(): LiveData<PricesRange>
    fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>>
}