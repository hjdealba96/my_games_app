package com.co.sunflowerslang.gamesapp.gamesfilter

import com.co.sunflowerslang.gamesapp.BasePresenter
import com.co.sunflowerslang.gamesapp.data.FilterParameters
import com.co.sunflowerslang.gamesapp.data.source.DefaultGamesRepository

class FilterPresenter : BasePresenter<FilterContract.View>(), FilterContract.Presenter {

    private val gamesRepository = DefaultGamesRepository()

    private var sortOption: FilterParameters.SortOption = FilterParameters.SortOption.DATE_ADDED
    private var minPrice: Float = 0.0F
    private var maxPrice: Float = 0.0F
    private var ratings: MutableList<Int> = mutableListOf()
    private var brands: MutableList<String> = mutableListOf()

    override fun init() {
        getView()?.checkDefaultSortOption()
        getView()?.showAllBrands(gamesRepository.getAllBrands())
        getView()?.showPricesRanges(gamesRepository.getPricesRange())
    }

    override fun setSortOptionByDownloads() {
        sortOption = FilterParameters.SortOption.DOWNLOADS
    }

    override fun setSortOptionByDateAdded() {
        sortOption = FilterParameters.SortOption.DATE_ADDED
    }

    override fun setSortOptionByPrice() {
        sortOption = FilterParameters.SortOption.PRICE
    }

    override fun setMinPriceToFilter(price: Float) {
        minPrice = price
    }

    override fun setMaxPriceToFilter(price: Float) {
        maxPrice = price
    }

    override fun addRatingToFilter(rating: Int) {
        ratings.add(rating)
    }

    override fun removeRatingFromFilter(rating: Int) {
        ratings.remove(rating)
    }

    override fun addBrandToFilter(brand: String) {
        brands.add(brand)
    }

    override fun removeBrandFromFilter(brand: String) {
        brands.remove(brand)
    }

    override fun generateFilter() {
        val filterParameters = FilterParameters(sortOption, minPrice, maxPrice, ratings, brands)
        getView()?.showAllGamesFiltered(filterParameters)
    }

}