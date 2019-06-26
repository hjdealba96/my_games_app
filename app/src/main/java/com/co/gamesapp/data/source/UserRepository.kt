package com.co.gamesapp.data.source

interface UserRepository {
    fun saveStart(started: Boolean)
    fun getStartStatus(): Boolean
}