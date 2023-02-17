package com.example.bitsocrypto.di

import android.content.Context
import androidx.room.Room
import com.example.bitsocrypto.data.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val NAME_DATABASE = "Currencies Bitso"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CurrencyDatabase::class.java, NAME_DATABASE).build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db:CurrencyDatabase) = db.getCurrencyDao()
}