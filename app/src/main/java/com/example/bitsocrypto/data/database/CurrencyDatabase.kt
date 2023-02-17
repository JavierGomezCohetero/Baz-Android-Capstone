package com.example.bitsocrypto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bitsocrypto.data.database.converters.BookConverter
import com.example.bitsocrypto.data.database.converters.TickerConverter
import com.example.bitsocrypto.data.database.dao.CurrencyDao
import com.example.bitsocrypto.data.database.entities.CurrencyRoom
import com.example.bitsocrypto.data.database.entities.Detail

@Database(entities = [CurrencyRoom::class, Detail::class], version = 1, exportSchema = false)
@TypeConverters(
    TickerConverter::class,
    BookConverter::class
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}