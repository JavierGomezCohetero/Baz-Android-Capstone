package com.example.bitsocrypto.data.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.bitsocrypto.domain.models.Ticker
import com.google.gson.Gson
import java.lang.reflect.Type

@ProvidedTypeConverter
class TickerConverter(private val list_ticker: Type, private val gson: Gson) {
    @TypeConverter
    fun fromString(value: String): Ticker {
        return Gson().fromJson(value, list_ticker)
    }

    @TypeConverter
    fun fromTicker(list: Ticker): String {
        return gson.toJson(list)
    }
}