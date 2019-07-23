package com.co.sunflowerslang.gamesapp.allgames

import com.co.sunflowerslang.gamesapp.data.Game

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