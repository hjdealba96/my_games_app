package com.co.gamesapp.data.source

import androidx.lifecycle.LiveData

interface UserRepository {
    fun saveStart(status: Boolean)
    fun getStartStatus(): LiveData<Boolean>
}