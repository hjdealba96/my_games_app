package com.co.gamesapp.data.source.local.games

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.PricesRange

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGames(games: List<Game>)

    @Query("SELECT * FROM game WHERE name IS NOT null ORDER BY name;")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE popular = 1 AND name IS NOT null ORDER BY name;")
    fun getPopularGames(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE name IS NOT null AND created_at IS NOT NULL ORDER BY datetime(created_at) DESC LIMIT :limit;")
    fun getNewGames(limit: Int): LiveData<List<Game>>

    @Query("SELECT DISTINCT universe FROM game WHERE name IS NOT null ORDER BY 1;")
    fun getAllGameNames(): LiveData<List<String>>

    @Query(
        "SELECT min(price.amount) AS min_amount, max(price.amount) AS max_amount " +
                "FROM (SELECT CAST(replace(replace(price, '.', ''), ',', '.') as float) AS amount " +
                "FROM game WHERE price IS NOT NULL) price;"
    )
    fun getPricesRange(): LiveData<PricesRange>

    @RawQuery(observedEntities = [Game::class])
    fun getFilteredGames(query: SupportSQLiteQuery): LiveData<List<Game>>
}