package com.co.sunflowerslang.gamesapp.onboarding

import com.co.sunflowerslang.gamesapp.BaseView

interface OnBoardingContract {
    interface View : BaseView{
        fun showFirstScreen()
        fun showAllGames()
    }

    interface Presenter {
        fun setStarted()
    }
}