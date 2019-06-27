package com.co.gamesapp.gamedetails

import com.co.gamesapp.BasePresenter
import com.co.gamesapp.data.source.DefaultGamesRepository

class GameDetailsPresenter(private val gameId: String) : BasePresenter<GameDetailsContract.View>() {

    private val gamesRepository = DefaultGamesRepository()

    override fun init() {
        getView()?.showGame(gamesRepository.getGame(gameId))
    }
}