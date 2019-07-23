package com.co.sunflowerslang.gamesapp.gamedetails

import androidx.lifecycle.LiveData
import com.co.sunflowerslang.gamesapp.BaseView
import com.co.sunflowerslang.gamesapp.data.Game

interface GameDetailsContract {
    interface View : BaseView{
        fun showGame(game: LiveData<Game>)
    }
}