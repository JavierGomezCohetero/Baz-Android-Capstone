package com.example.bitsocrypto.domain.models

import android.os.Parcelable
import com.example.bitsocrypto.data.database.entities.CurrencyRoom
import com.example.bitsocrypto.data.model.CurrencyModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    var bitso_id: Int = 0,
    val book: String,
    val default_chart: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String,
    val tick_size: String,
    var img_url: String,
    var name: String
) : Parcelable

fun CurrencyModel.toDomain() = Currency(
    bitso_id,
    book,
    default_chart,
    maximum_amount,
    maximum_price,
    maximum_value,
    minimum_amount,
    minimum_price,
    minimum_value,
    tick_size,
    img_url,
    name
)

fun CurrencyRoom.toDomain() = Currency(
    bitso_id,
    book,
    default_chart,
    maximum_amount,
    maximum_price,
    maximum_value,
    minimum_amount,
    minimum_price,
    minimum_value,
    tick_size,
    img_url,
    name
)