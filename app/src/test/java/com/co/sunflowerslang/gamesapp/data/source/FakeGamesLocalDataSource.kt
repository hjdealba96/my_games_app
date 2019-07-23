package com.co.sunflowerslang.gamesapp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.data.MainCoroutineRule
import com.co.sunflowerslang.gamesapp.data.PricesRange
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class FakeGamesLocalDataSource : GamesLocalDataSource {

    @get:Rule
    val rule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var games: List<Game> = mutableListOf()

    override fun saveGames(games: List<Game>) {
        this.games = games
    }

    override fun getPopularGames(): LiveData<List<Game>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewGames(): LiveData<List<Game>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllGameNames(): LiveData<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPricesRange(): LiveData<PricesRange> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilteredGames(filterParameters: FilterParameters): LiveData<List<Game>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllGames(): LiveData<List<Game>> = liveData {
        emit(games)
    }
    
    override fun getGame(id: String): LiveData<Game> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}