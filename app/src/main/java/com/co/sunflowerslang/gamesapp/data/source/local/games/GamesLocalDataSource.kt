package com.co.sunflowerslang.gamesapp.data.source.local.games

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.co.sunflowerslang.gamesapp.GamesApplication
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.data.PricesRange
import com.co.sunflowerslang.gamesapp.data.source.GamesLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GamesLocalDataSource : GamesLocalDataSource {

    companion object {
        private const val RECENTS_GAMES_LIMIT = 5
    }

    private val gamesDao = GamesApplication.roomDatabase.gamesDao()

    override fun saveGames(games: List<Game>) {
        if (games.isNotEmpty()) {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    gamesDao.saveGames(games)
                }
            }
        }
    }

    override fun getGame(id: String): LiveData<Game> = gamesDao.getGame(id)

    override fun getAllGames(): LiveData<List<Game>> = gamesDao.getAllGames()

    override fun getPopularGames(): LiveData<List<Game>> = gamesDao.getPopularGames()

    override fun getNewGames(): LiveData<List<Game>> = gamesDao.getNewGames(RECENTS_GAMES_LIMIT)

    override fun getAllGameNames(): LiveData<List<String>> = gamesDao.getAllGameNames()

    override fun getPricesRange(): LiveData<PricesRange> = gamesDao.getPricesRange()

    override fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>> =
        gamesDao.getFilteredGames(SimpleSQLiteQuery(createGamesFilterQuery(filterParameters)))

    private fun createGamesFilterQuery(filterParameters: FilterParameters): String =
        "SELECT * FROM game " +
                "WHERE cast(replace(replace(price, '.', ''), ',', '.') as float) BETWEEN ${filterParameters.minAmount} AND ${filterParameters.maxAmount} " +
                "${if (filterParameters.ratings.isNotEmpty()) {
                    " AND CAST(rating AS integer) IN (${filterParameters.ratings.joinToString(",")}) "
                } else {
                    ""
                }} ${if (filterParameters.brands.isNotEmpty()) {
                    " AND universe IN (${filterParameters.brands.joinToString("','", "'", "'")}) "
                } else {
                    ""
                }} ${when (filterParameters.sortOption) {
                    FilterParameters.SortOption.PRICE -> " ORDER BY cast(replace(replace(price, '.', ''), ',', '.') as float) DESC;"
                    FilterParameters.SortOption.DATE_ADDED -> " ORDER BY datetime(created_at) DESC;"
                    FilterParameters.SortOption.DOWNLOADS -> " ORDER BY cast(downloads as integer) DESC;"
                }}"
}