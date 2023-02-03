package com.example.bitsocrypto.domain

import com.example.bitsocrypto.data.model.*
import com.example.bitsocrypto.data.network.CurrencyService

class CurrencyRepository {
    private val api = CurrencyService()

    suspend fun getAllCurrencies(): List<CurrencyModel> = api.getCurrencies()

    suspend fun getCurrencyDetail(currencyName: String): CurrencyDetailModel =
        api.getCurrencyDetail(currencyName)

    suspend fun getTicker(currencyName: String): TickerModel = api.getTicker(currencyName)

    suspend fun getIcons(): List<IconResultModelItem> = api.getIcons()
}