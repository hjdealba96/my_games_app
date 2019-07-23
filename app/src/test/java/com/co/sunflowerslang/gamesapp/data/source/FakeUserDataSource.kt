package com.co.sunflowerslang.gamesapp.data.source

class FakeUserDataSource(private var startStatus: Boolean = false) : UserDataSource {

    override fun saveStart(status: Boolean) {
        this.startStatus = status
    }

    override fun getStartStatus(): Boolean = this.startStatus

}