package com.co.sunflowerslang.gamesapp.allgames.adapter

interface IBrandsAdapterBuilder {
    fun setBrands(brands: List<String>): IBrandsAdapterBuilder
    fun setOnFilterOptionClicked(onFilterOptionClicked: (String) -> Unit): IBrandsAdapterBuilder
    fun setOnClearFilterOptionClicked(onClearFilterOptionClicked: () -> Unit): IBrandsAdapterBuilder
    fun create(): BrandsAdapter
}