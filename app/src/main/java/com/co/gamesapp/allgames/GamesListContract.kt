package com.co.gamesapp.allgames

import com.co.gamesapp.data.Game

interface GamesListContract {
    interface View {
        fun updateView()
    }

    interface Presenter {
        fun filterByBrand(universe: String)
        fun clearFilter()
        fun getGamesCount(): Int
        fun getGameAtPosition(position: Int): Game
    }
}