package com.co.sunflowerslang.gamesapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.data.PricesRange
import com.co.sunflowerslang.gamesapp.data.source.remote.GamesRemoteDataSource
import com.co.sunflowerslang.gamesapp.data.source.local.games.GamesLocalDataSource

class DefaultGamesRepository(
    private val remoteDataSource: GamesDataSource = GamesRemoteDataSource(),
    private val localDataSource: com.co.sunflowerslang.gamesapp.data.source.GamesLocalDataSource = GamesLocalDataSource()
) : GamesRepository {

    override fun getGame(id: String): LiveData<Game> = localDataSource.getGame(id)

    override fun getAllGames(): LiveData<List<Game>> {
        val mediator = MediatorLiveData<List<Game>>()
        mediator.addSource(remoteDataSource.getAllGames()) { localDataSource.saveGames(it) }
        mediator.addSource(localDataSource.getAllGames()) { mediator.value = it }
        return mediator
    }

    override fun getPopularGames(): LiveData<List<Game>> = localDataSource.getPopularGames()

    override fun getNewGames(): LiveData<List<Game>> = localDataSource.getNewGames()

    override fun getAllBrands(): LiveData<List<String>> = localDataSource.getAllGameNames()

    override fun getPricesRange(): LiveData<PricesRange> = localDataSource.getPricesRange()

    override fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>> =
        localDataSource.getFilteredGames(filterParameters)
}