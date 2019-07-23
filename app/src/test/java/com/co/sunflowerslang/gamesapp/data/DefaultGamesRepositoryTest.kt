package com.co.sunflowerslang.gamesapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.sunflowerslang.gamesapp.data.source.DefaultGamesRepository
import com.co.sunflowerslang.gamesapp.data.source.FakeGamesLocalDataSource
import com.co.sunflowerslang.gamesapp.data.source.FakeGamesRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultGamesRepositoryTest {

    /**
     * Required to mock UI-thread
     * */
    @get:Rule
    val rule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var gamesRepository: DefaultGamesRepository
    private lateinit var gamesRemoteDataSource: FakeGamesRemoteDataSource

    @Before
    fun createRepository() {
        gamesRemoteDataSource = FakeGamesRemoteDataSource()
        gamesRepository = DefaultGamesRepository(gamesRemoteDataSource, FakeGamesLocalDataSource())
    }

    @Test
    fun test_emptyGames() {
        gamesRepository.getAllGames().observeForever {
            assertEquals(it.isEmpty(), true)
        }
    }

    @Test
    fun test_gamesLoad() = runBlockingTest {
        val generatedGames = generateGames()
        gamesRemoteDataSource.games = generatedGames
        gamesRepository.getAllGames().observeForever {
            assertEquals(it.isNullOrEmpty(), false)
            assertThat(it, `is`(generatedGames))
        }
    }

    private fun generateGames(): List<Game> {
        val games = mutableListOf<Game>()
        games.add(
            Game(
                "1",
                "Game 1",
                "2019-06-19T19:13:19.646Z",
                "2018-07-06T16:46:18.292Z",
                "69,99",
                "https://www.smashbros.com/wiiu-3ds/images/character/mewtwo/main.png",
                true,
                "2",
                "2000",
                "Test game",
                "5498489",
                "Test universe",
                "Alien"
            )
        )
        games.add(
            Game(
                "2",
                "Game 2",
                "2019-06-19T19:13:19.646Z",
                "2018-07-06T16:46:18.292Z",
                "79,99",
                "https://www.smashbros.com/wiiu-3ds/images/character/mewtwo/main.png",
                false,
                "4",
                "500",
                "Test game",
                "5498489",
                "Test universe",
                "Human"
            )
        )
        games.add(
            Game(
                "3",
                "Game 3",
                "2019-06-19T19:13:19.646Z",
                "2018-07-06T16:46:18.292Z",
                "99,99",
                "https://www.smashbros.com/wiiu-3ds/images/character/mewtwo/main.png",
                false,
                "5",
                "1500",
                "Test game",
                "5498489",
                "Test universe",
                "Animal"
            )
        )
        return games
    }

}