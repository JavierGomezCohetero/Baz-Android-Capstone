package com.example.bitsocrypto.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitsocrypto.data.database.entities.CurrencyRoom
import com.example.bitsocrypto.data.database.entities.Detail

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies_table")
    suspend fun getAllCurrencies(): List<CurrencyRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyRoom>)

    @Query("DELETE FROM currencies_table")
    suspend fun deleteAllCurrencies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(data: List<Detail>)

    @Query("SELECT * FROM detail_table")
    suspend fun getAllDetails(): List<Detail>

    @Query("DELETE FROM detail_table")
    suspend fun deleteAllDetails()

}