package com.example.bitsocrypto.domain.repository

import com.example.bitsocrypto.data.model.*
import com.example.bitsocrypto.domain.models.*
import io.reactivex.rxjava3.core.Single

interface CurrencyRepository {

    suspend fun getAllCurrencies(): List<Currency>

    suspend fun getIcons(): List<IconResultModelItem>

    fun getBook(id: String): Single<Book>

    fun getTicker(id: String): Single<Ticker>

    suspend fun getAllCurrencyDatabase(): List<Currency>

    suspend fun insertCurrencies(currencies: List<Currency>)

    suspend fun clearCurrencies()

    fun getDetail(): List<Details>

    fun insertDetail(detail: List<Details>)

    fun clearDetail()
}