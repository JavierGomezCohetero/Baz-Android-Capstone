package com.example.bitsocrypto.data.network

import com.example.bitsocrypto.core.RetrofitHelper
import com.example.bitsocrypto.data.model.CurrencyDetailModel
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.data.model.TickerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val icon = RetrofitHelper.getIcons()

    suspend fun getCurrencies(): List<CurrencyModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CurrencyApiClient::class.java).getAllCurrencies()
            response.body()?.payload ?: emptyList()
        }
    }

    suspend fun getCurrencyDetail(currencyName: String): CurrencyDetailModel {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(CurrencyApiClient::class.java).getCurrencyDetail(currencyName)
            response.body()?.payload!!
        }
    }

    suspend fun getTicker(currencyName: String): TickerModel {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CurrencyApiClient::class.java).getTickers(currencyName)
            response.body()?.payload!!
        }
    }

    suspend fun getIcons(): List<IconResultModelItem> {
        return withContext(Dispatchers.IO) {
            val response = icon.create(IconsApiClient::class.java).getIcons()
            response.body() ?: emptyList()
        }
    }
}