package com.co.sunflowerslang.gamesapp.splash

import com.co.sunflowerslang.gamesapp.BasePresenter
import com.co.sunflowerslang.gamesapp.PreferencesManager
import com.co.sunflowerslang.gamesapp.data.source.DefaultUserRepository
import com.co.sunflowerslang.gamesapp.data.source.local.user.UserLocalDataSource

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