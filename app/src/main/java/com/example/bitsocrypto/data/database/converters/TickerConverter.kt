package com.example.bitsocrypto.data.database.converters

import androidx.room.TypeConverter
import com.example.bitsocrypto.domain.models.Ticker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TickerConverter {
    @TypeConverter
    fun fromString(value: String): Ticker {
        val listType = object : TypeToken<Ticker>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTicker(list: Ticker): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}