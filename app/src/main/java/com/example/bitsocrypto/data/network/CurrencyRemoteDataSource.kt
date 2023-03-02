package com.example.bitsocrypto.data.network

import com.example.bitsocrypto.data.model.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRemoteDataSource @Inject constructor(
    private val api: CurrencyApiClient,
    private val icon_service: IconApiClient
) {

    suspend fun getCurrencies(): List<CurrencyModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCurrencies()
            response.body()?.payload ?: emptyList()
        }
    }

    fun getBook(currency_name: String): Single<CurrencyDetailResultModel> {
        return api.getBook(currency_name)
    }

    fun getTicker(currencyName: String): Single<TickerResultModel> {
        return api.getTickers(currencyName)
    }

    suspend fun getIcons(): List<IconResultModelItem> {
        return withContext(Dispatchers.IO) {
            val response = icon_service.getIcons()
            response.body() ?: emptyList()
        }
    }
}