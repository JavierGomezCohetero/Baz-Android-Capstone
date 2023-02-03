package com.example.bitsocrypto.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyModel(
    val book: String = "",
    val default_chart: String = "",
    val maximum_amount: String = "",
    val maximum_price: String = "",
    val maximum_value: String = "",
    val minimum_amount: String = "",
    val minimum_price: String = "",
    val minimum_value: String = "",
    val tick_size: String = "",
    var imgUrl: String = "",
    var name: String = ""
) : Parcelable