package com.example.bitsocrypto.di

import android.content.Context
import androidx.room.Room
import com.example.bitsocrypto.data.database.CurrencyDatabase
import com.example.bitsocrypto.data.database.converters.BookConverter
import com.example.bitsocrypto.data.database.converters.TickerConverter
import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Ticker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val NAME_DATABASE = "Currencies Bitso"
    private val gson = Gson()

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CurrencyDatabase::class.java, NAME_DATABASE)
            .addTypeConverter(BookConverter(provideConverter("book"), gson))
            .addTypeConverter(TickerConverter(provideConverter("ticker"), gson))
            .build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db: CurrencyDatabase) = db.getCurrencyDao()

    @Singleton
    @Provides
    fun provideConverter(name: String): Type {
        return if (name == "book") {
            object : TypeToken<Book>() {}.type
        } else {
            object : TypeToken<Ticker>() {}.type
        }
    }
}