package com.example.bitsocrypto.data.network


import com.example.bitsocrypto.data.model.CurrencyDetailResultModel
import com.example.bitsocrypto.data.model.CurrencyResultModel
import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.data.model.TickerResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiClient {
    @GET("available_books/")
    suspend fun getAllCurrencies(): Response<CurrencyResultModel>

    @GET("order_book/")
    suspend fun getBook (@Query("book") book: String): Response<CurrencyDetailResultModel>

    @GET("ticker/")
    suspend fun getTickers(@Query("book") book: String): Response<TickerResultModel>

}