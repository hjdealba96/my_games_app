package com.co.sunflowerslang.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.data.PricesRange

interface GamesRepository {
    fun getGame(id: String): LiveData<Game>
    fun getAllGames(): LiveData<List<Game>>
    fun getPopularGames(): LiveData<List<Game>>
    fun getNewGames(): LiveData<List<Game>>
    fun getAllBrands(): LiveData<List<String>>
    fun getPricesRange(): LiveData<PricesRange>
    fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>>
}