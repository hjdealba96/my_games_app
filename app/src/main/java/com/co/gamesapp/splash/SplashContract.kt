package com.co.gamesapp.splash

import com.co.gamesapp.BaseView

interface SplashContract {
    interface View : BaseView {
        fun showOnBoardingScreen()
        fun showAllGamesScreen()
    }
}