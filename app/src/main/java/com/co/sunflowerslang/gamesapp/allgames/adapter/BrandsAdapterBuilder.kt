package com.co.sunflowerslang.gamesapp.allgames.adapter

class BrandsAdapterBuilder : IBrandsAdapterBuilder {

    private lateinit var brands: List<String>
    private lateinit var onFilterOptionClicked: (String) -> Unit
    private lateinit var onClearFilterOptionClicked: () -> Unit

    override fun setBrands(brands: List<String>): IBrandsAdapterBuilder {
        this.brands = brands
        return this
    }

    override fun setOnFilterOptionClicked(onFilterOptionClicked: (String) -> Unit): IBrandsAdapterBuilder {
        this.onFilterOptionClicked = onFilterOptionClicked
        return this
    }

    override fun setOnClearFilterOptionClicked(onClearFilterOptionClicked: () -> Unit): IBrandsAdapterBuilder {
        this.onClearFilterOptionClicked = onClearFilterOptionClicked
        return this
    }

    override fun create(): BrandsAdapter =
        BrandsAdapter(brands, onFilterOptionClicked, onClearFilterOptionClicked)

}