package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData
import com.co.gamesapp.PreferencesManager
import com.co.gamesapp.data.source.local.user.UserLocalDataSource

class DefaultUserRepository(preferencesManager: PreferencesManager) : UserRepository {

    private val localDataSource = UserLocalDataSource(preferencesManager)

    override fun saveStart(status: Boolean) {
        localDataSource.saveStart(status)
    }

    override fun getStartStatus(): LiveData<Boolean> = localDataSource.getStartStatus()

}