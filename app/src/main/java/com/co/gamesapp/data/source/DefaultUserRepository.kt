package com.co.gamesapp.data.source

import com.co.gamesapp.PreferencesManager
import com.co.gamesapp.data.source.local.user.UserLocalDataSource

class DefaultUserRepository(preferencesManager: PreferencesManager) : UserRepository {

    private val localDataSource = UserLocalDataSource(preferencesManager)

    override fun saveStart(started: Boolean) {
        localDataSource.saveStart(started)
    }

    override fun getStartStatus(): Boolean = localDataSource.getStartStatus()

}