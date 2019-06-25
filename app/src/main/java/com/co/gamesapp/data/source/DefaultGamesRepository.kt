package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.PricesRange
import com.co.gamesapp.data.source.remote.GamesRemoteDataSource
import com.co.gamesapp.data.source.local.games.GamesLocalDataSource

class DefaultGamesRepository : GamesRepository {

    private val remoteDataSource = GamesRemoteDataSource()
    private val localDataSource = GamesLocalDataSource()

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