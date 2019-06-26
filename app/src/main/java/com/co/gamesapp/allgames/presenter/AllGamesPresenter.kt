package com.co.gamesapp.allgames.presenter

import com.co.gamesapp.BasePresenter
import com.co.gamesapp.allgames.AllGamesContract
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.source.DefaultGamesRepository

class AllGamesPresenter : BasePresenter<AllGamesContract.View>(),
    AllGamesContract.Presenter {

    private val gamesRepository = DefaultGamesRepository()

    override fun init() {
        getView()?.showAllGames(gamesRepository.getAllGames())
        getView()?.showNewGames(gamesRepository.getNewGames())
        getView()?.showPopularGames(gamesRepository.getPopularGames())
        getView()?.showAllBrands(gamesRepository.getAllBrands())
    }

    override fun filterGames(filterParameters: FilterParameters) {
        getView()?.showFilteredGames(gamesRepository.getFilteredGames(filterParameters))
    }

}