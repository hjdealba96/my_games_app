package com.co.gamesapp.gamesfilter

import androidx.lifecycle.LiveData
import com.co.gamesapp.BaseView
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.PricesRange

interface FilterContract {
    interface View : BaseView {
        fun checkDefaultSortOption()
        fun showAllBrands(brands: LiveData<List<String>>)
        fun showPricesRanges(ranges: LiveData<PricesRange>)
        fun showAllGamesFiltered(filterParameters: FilterParameters)
    }

    interface Presenter {
        fun setSortOptionByDownloads()
        fun setSortOptionByDateAdded()
        fun setSortOptionByPrice()
        fun setMinPriceToFilter(price: Float)
        fun setMaxPriceToFilter(price: Float)
        fun addRatingToFilter(rating: Int)
        fun removeRatingFromFilter(rating: Int)
        fun addBrandToFilter(brand: String)
        fun removeBrandFromFilter(brand: String)
        fun generateFilter()
    }
}