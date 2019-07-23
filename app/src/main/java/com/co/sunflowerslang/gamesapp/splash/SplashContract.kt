package com.co.sunflowerslang.gamesapp.splash

import com.co.sunflowerslang.gamesapp.BaseView

interface SplashContract {
    interface View : BaseView {
        fun showOnBoardingScreen()
        fun showAllGamesScreen()
    }
}