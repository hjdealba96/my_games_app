package com.co.sunflowerslang.gamesapp.data.source

interface UserDataSource {
    fun saveStart(status: Boolean)
    fun getStartStatus(): Boolean
}