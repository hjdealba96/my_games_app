package com.co.gamesapp.allgames.presenter

import com.co.gamesapp.allgames.GamesListContract
import com.co.gamesapp.data.Game

class GamesListPresenter(private val games: List<Game>) : GamesListContract.Presenter {

    private var view: GamesListContract.View? = null
    private var filteredGames: List<Game>? = null

    fun setView(view: GamesListContract.View) {
        this.view = view
    }

    override fun filterByBrand(universe: String) {
        filteredGames = games.filter { it.universe == universe }
        view?.updateView()
    }

    override fun clearFilter() {
        filteredGames = null
        view?.updateView()
    }

    override fun getGamesCount(): Int = filteredGames?.size ?: games.size

    override fun getGameAtPosition(position: Int): Game = filteredGames?.get(position) ?: games[position]

}