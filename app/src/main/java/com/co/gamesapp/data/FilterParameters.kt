package com.co.gamesapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterParameters(
    var sortOption: SortOption = SortOption.DATE_ADDED,
    var minAmount: Float = 0.0F,
    var maxAmount: Float = 0.0F,
    var ratings: MutableList<Int> = mutableListOf(),
    var brands: MutableList<String> = mutableListOf()
) : Parcelable {
    @Parcelize
    enum class SortOption : Parcelable { DOWNLOADS, DATE_ADDED, PRICE }
}