package com.co.gamesapp.allgames

import androidx.lifecycle.LiveData
import com.co.gamesapp.BaseView
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.Game

interface AllGamesContract {
    interface View : BaseView {
        fun showAllBrands(brands: LiveData<List<String>>)
        fun showPopularGames(games: LiveData<List<Game>>)
        fun showNewGames(games: LiveData<List<Game>>)
        fun showAllGames(games: LiveData<List<Game>>)
        fun showFilteredGames(games: LiveData<List<Game>>)
    }

    interface Presenter {
        fun filterGames(filterParameters: FilterParameters)
    }

    interface Interactor {
        fun loadAllGames()
        fun loadPopularGames()
        fun loadRecentGames()
    }
}