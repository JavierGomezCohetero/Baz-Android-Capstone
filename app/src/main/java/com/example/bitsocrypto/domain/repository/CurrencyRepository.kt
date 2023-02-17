package com.example.bitsocrypto.domain.repository

import com.example.bitsocrypto.data.database.dao.CurrencyDao
import com.example.bitsocrypto.data.database.entities.toDatabase
import com.example.bitsocrypto.data.model.*
import com.example.bitsocrypto.data.network.CurrencyRemoteDataSource
import com.example.bitsocrypto.domain.models.*
import com.example.bitsocrypto.utils.extensions.stringConvert
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyRemoteDataSource, private val currencyDao: CurrencyDao
) {


    // FROM API
    suspend fun getAllCurrencies(): List<Currency> = api.getCurrencies().map { it.toDomain() }

    suspend fun getBook(currencyName: String): Book =
        api.getBook(currencyName).toDomain()

    suspend fun getTicker(currencyName: String): Ticker = api.getTicker(currencyName).toDomain()

    suspend fun getIcons(): List<IconResultModelItem> = api.getIcons()

    // FROM ROOM

    suspend fun getAllCurrencyDatabase(): List<Currency> =
        currencyDao.getAllCurrencies().map { it.toDomain() }

    suspend fun insertCurrencies(currencies: List<Currency>) =
        currencyDao.insertAll(currencies.map { it.toDatabase() })

    suspend fun clearCurrencies() = currencyDao.deleteAllCurrencies()


    suspend fun getDetail(): List<Details> = currencyDao.getAllDetails().map { it.toDatabase() }

    suspend fun insertDetail(detail: List<Details>) =
        currencyDao.insertDetails(detail.map { it.toDatabase() })

    suspend fun clearDetail() = currencyDao.deleteAllDetails()


}