package com.co.sunflowerslang.gamesapp.allgames.presenter

import com.co.sunflowerslang.gamesapp.BasePresenter
import com.co.sunflowerslang.gamesapp.allgames.AllGamesContract
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.source.DefaultGamesRepository

class AllGamesPresenter : BasePresenter<AllGamesContract.View>(),
    AllGamesContract.Presenter {

    private val gamesRepository = DefaultGamesRepository()

    override fun init() {
        loadGamesList()
    }

    override fun loadGamesList() {
        getView()?.showAllGames(gamesRepository.getAllGames())
        getView()?.showNewGames(gamesRepository.getNewGames())
        getView()?.showPopularGames(gamesRepository.getPopularGames())
        getView()?.showAllBrands(gamesRepository.getAllBrands())
    }

    override fun filterGames(filterParameters: FilterParameters) {
        getView()?.showFilteredGames(gamesRepository.getFilteredGames(filterParameters))
    }

}