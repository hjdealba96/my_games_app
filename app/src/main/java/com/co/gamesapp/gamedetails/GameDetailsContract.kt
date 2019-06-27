package com.co.gamesapp.gamedetails

import androidx.lifecycle.LiveData
import com.co.gamesapp.BaseView
import com.co.gamesapp.data.Game

interface GameDetailsContract {
    interface View : BaseView{
        fun showGame(game: LiveData<Game>)
    }
}