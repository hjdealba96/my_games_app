package com.co.sunflowerslang.gamesapp.onboarding

import com.co.sunflowerslang.gamesapp.BasePresenter
import com.co.sunflowerslang.gamesapp.PreferencesManager
import com.co.sunflowerslang.gamesapp.data.source.DefaultUserRepository
import com.co.sunflowerslang.gamesapp.data.source.local.user.UserLocalDataSource

class OnBoardingPresenter(preferencesManager: PreferencesManager) : BasePresenter<OnBoardingContract.View>(),
    OnBoardingContract.Presenter {

    private val userRepository = DefaultUserRepository(UserLocalDataSource(preferencesManager))

    override fun init() {
        getView()?.showFirstScreen()
    }

    override fun setStarted() {
        userRepository.saveStart(started = true)
        getView()?.showAllGames()
    }
}