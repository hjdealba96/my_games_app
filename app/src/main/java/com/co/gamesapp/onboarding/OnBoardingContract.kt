package com.co.gamesapp.onboarding

import com.co.gamesapp.BaseView

interface OnBoardingContract {
    interface View : BaseView{
        fun showFirstScreen()
        fun showAllGames()
    }

    interface Presenter {
        fun setStarted()
    }
}