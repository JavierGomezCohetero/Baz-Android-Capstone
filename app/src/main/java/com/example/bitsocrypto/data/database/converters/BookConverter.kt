package com.example.bitsocrypto.data.database.converters

import androidx.room.TypeConverter
import com.example.bitsocrypto.domain.models.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BookConverter {
    @TypeConverter
    fun fromString(value: String): Book {
        val listType = object : TypeToken<Book>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromBook(list: Book): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}