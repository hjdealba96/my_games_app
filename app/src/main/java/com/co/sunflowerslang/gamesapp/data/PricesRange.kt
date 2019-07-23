package com.co.sunflowerslang.gamesapp.data

import androidx.room.ColumnInfo

data class PricesRange(
    @ColumnInfo(name = "min_amount")
    val minAmount: Float,
    @ColumnInfo(name = "max_amount")
    val maxAmount: Float
)