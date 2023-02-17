package com.example.bitsocrypto.di

import com.example.bitsocrypto.data.network.CurrencyApiClient
import com.example.bitsocrypto.data.network.IconApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bitso.com/v3/")
            .client(provideOkkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("User-Agent", "Bitso Crypto")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun provideCurrencyApiClient(retrofit: Retrofit): CurrencyApiClient {
        return retrofit.create(CurrencyApiClient::class.java)
    }

}