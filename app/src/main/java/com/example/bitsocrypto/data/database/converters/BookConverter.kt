package com.example.bitsocrypto.data.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.bitsocrypto.domain.models.Book
import com.google.gson.Gson
import java.lang.reflect.Type

@ProvidedTypeConverter
class BookConverter(private val list_book: Type, private val gson: Gson) {
    @TypeConverter
    fun fromString(value: String): Book {
        return Gson().fromJson(value, list_book)
    }

    @TypeConverter
    fun fromBook(list: Book): String {
        return gson.toJson(list)
    }
}