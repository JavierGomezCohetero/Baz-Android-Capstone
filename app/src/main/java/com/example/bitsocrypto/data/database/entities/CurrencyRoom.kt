package com.example.bitsocrypto.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocrypto.domain.models.Currency

@Entity(tableName = "currencies_table")
data class CurrencyRoom(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo("bitsoId") val bitsoId: Int = 0,
    @ColumnInfo("book") val book: String = "",
    @ColumnInfo(name = "default_chart") val default_chart: String = "",
    @ColumnInfo(name = "maximum_amount") val maximum_amount: String = "",
    @ColumnInfo(name = "maximum_price") val maximum_price: String = "",
    @ColumnInfo(name = "maximum_value") val maximum_value: String = "",
    @ColumnInfo(name = "minimum_amount") val minimum_amount: String = "",
    @ColumnInfo(name = "minimum_price") val minimum_price: String = "",
    @ColumnInfo(name = "minimum_value") val minimum_value: String = "",
    @ColumnInfo(name = "tick_size") val tick_size: String = "",
    @ColumnInfo("img_url") val img_url: String = "",
    @ColumnInfo("name") val name: String = ""
)

fun Currency.toDatabase() = CurrencyRoom(
    bitsoId = bitsoId,
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