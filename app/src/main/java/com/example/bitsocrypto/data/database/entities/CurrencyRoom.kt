package com.example.bitsocrypto.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocrypto.domain.models.Currency

@Entity(tableName = "currencies_table")
data class CurrencyRoom(
    @PrimaryKey val id: Int? = null,
    val bitso_id: Int = 0,
    val book: String = "",
    val default_chart: String = "",
    val maximum_amount: String = "",
    val maximum_price: String = "",
    val maximum_value: String = "",
    val minimum_amount: String = "",
    val minimum_price: String = "",
    val minimum_value: String = "",
    val tick_size: String = "",
    val img_url: String = "",
    val name: String = ""
)

fun Currency.toDatabase() = CurrencyRoom(
    bitso_id = bitso_id,
    book = book,
    default_chart = default_chart,
    maximum_amount = maximum_amount,
    maximum_price = maximum_price,
    maximum_value = maximum_value,
    minimum_amount = minimum_amount,
    minimum_price = minimum_price,
    minimum_value = minimum_value,
    tick_size = tick_size,
    img_url = img_url,
    name = name
)