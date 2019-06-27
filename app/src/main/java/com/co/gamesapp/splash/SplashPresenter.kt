package com.co.gamesapp.splash

import com.co.gamesapp.BasePresenter
import com.co.gamesapp.PreferencesManager
import com.co.gamesapp.data.source.DefaultUserRepository
import com.co.gamesapp.data.source.local.user.UserLocalDataSource

class SplashPresenter(preferencesManager: PreferencesManager) : BasePresenter<SplashContract.View>() {

    private val userRepository = DefaultUserRepository(UserLocalDataSource(preferencesManager))

    override fun init() {
        val startStatus = userRepository.getStartStatus()
        if (startStatus) {
            getView()?.showAllGamesScreen()
        } else {
            getView()?.showOnBoardingScreen()
        }
    }

}