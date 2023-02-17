package com.example.bitsocrypto.data.network

import com.example.bitsocrypto.data.model.IconResultModelItem
import retrofit2.Response
import retrofit2.http.GET

interface IconApiClient {
    @GET("ErikThiart/cryptocurrency-icons/master/coin_map.json")
    suspend fun getIcons(): Response<List<IconResultModelItem>>
}