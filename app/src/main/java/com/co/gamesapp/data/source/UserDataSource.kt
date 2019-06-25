package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData

interface UserDataSource {
    fun saveStart(status: Boolean)
    fun getStartStatus(): LiveData<Boolean>
}