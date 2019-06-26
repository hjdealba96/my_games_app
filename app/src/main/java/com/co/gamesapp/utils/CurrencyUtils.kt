package com.co.gamesapp.utils

import java.text.NumberFormat
import java.util.*

fun numberToCurrency(locale: Locale, value: Float): String = NumberFormat.getCurrencyInstance(locale).format(value)