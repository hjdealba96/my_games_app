package com.co.sunflowerslang.gamesapp.gamedetails

import com.co.sunflowerslang.gamesapp.BasePresenter
import com.co.sunflowerslang.gamesapp.data.source.DefaultGamesRepository

class GameDetailsPresenter(private val gameId: String) : BasePresenter<GameDetailsContract.View>() {

    private val gamesRepository = DefaultGamesRepository()

    override fun init() {
        getView()?.showGame(gamesRepository.getGame(gameId))
    }
}