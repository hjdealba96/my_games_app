package com.co.gamesapp.data.source.local.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.co.gamesapp.PreferencesManager
import com.co.gamesapp.data.source.UserDataSource

class UserLocalDataSource(private val preferencesManager: PreferencesManager) : UserDataSource {

    companion object {
        private const val GET_STARTED = "get-started"
    }

    override fun saveStart(status: Boolean) {
        preferencesManager.addBooleanProperty(GET_STARTED, status)
    }

    override fun getStartStatus(): LiveData<Boolean> = liveData {
        emit(preferencesManager.getBooleanProperty(GET_STARTED))
    }

}