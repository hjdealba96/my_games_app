package com.co.gamesapp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class FakeGamesRemoteDataSource(var games: List<Game> = mutableListOf()) : GamesDataSource {

    @get:Rule
    val rule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    override fun getAllGames(): LiveData<List<Game>> = liveData {
        emit(games)
    }
}