package com.example.bitsocrypto.data.network

import com.example.bitsocrypto.core.RetrofitHelper
import com.example.bitsocrypto.data.model.CurrencyDetailModel
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.data.model.TickerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRemoteDataSource @Inject constructor(private val api: CurrencyApiClient) {
    private val icon = RetrofitHelper.getIcons()

    suspend fun getCurrencies(): List<CurrencyModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCurrencies()
            response.body()?.payload ?: emptyList()
        }
    }

    suspend fun getBook(currencyName: String): CurrencyDetailModel {
        return withContext(Dispatchers.IO) {
            val response = api.getBook(currencyName)
            response.body()?.payload!!
        }
    }

    suspend fun getTicker(currencyName: String): TickerModel {
        return withContext(Dispatchers.IO) {
            val response = api.getTickers(currencyName)
            response.body()?.payload!!
        }
    }

    suspend fun getIcons(): List<IconResultModelItem> {
        return withContext(Dispatchers.IO) {
            val response = icon.create(IconApiClient::class.java).getIcons()
            response.body() ?: emptyList()
        }
    }
}