package com.co.gamesapp.data.source

class FakeUserDataSource : UserDataSource {

    private var startStatus = false

    override fun saveStart(status: Boolean) {
        this.startStatus = status
    }

    override fun getStartStatus(): Boolean = this.startStatus

}