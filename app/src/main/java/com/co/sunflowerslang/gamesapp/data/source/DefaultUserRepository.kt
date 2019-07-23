package com.co.sunflowerslang.gamesapp.data.source

class DefaultUserRepository(private val localDataSource: UserDataSource) : UserRepository {

    override fun saveStart(started: Boolean) {
        localDataSource.saveStart(started)
    }

    override fun getStartStatus(): Boolean = localDataSource.getStartStatus()

}