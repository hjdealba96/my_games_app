package com.co.sunflowerslang.gamesapp.data.source.local.user

import com.co.sunflowerslang.gamesapp.PreferencesManager
import com.co.sunflowerslang.gamesapp.data.source.UserDataSource

class UserLocalDataSource(private val preferencesManager: PreferencesManager) : UserDataSource {

    companion object {
        private const val GET_STARTED = "get-started"
    }

    override fun saveStart(status: Boolean) {
        preferencesManager.addBooleanProperty(GET_STARTED, status)
    }

    override fun getStartStatus(): Boolean = preferencesManager.getBooleanProperty(GET_STARTED)

}