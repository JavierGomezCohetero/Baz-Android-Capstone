package com.example.bitsocrypto.di

import com.example.bitsocrypto.data.database.dao.CurrencyDao
import com.example.bitsocrypto.data.network.CurrencyApiClient
import com.example.bitsocrypto.data.network.CurrencyRemoteDataSource
import com.example.bitsocrypto.data.network.IconApiClient
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.domain.repository.CurrencyRepositoryImpl
import com.example.bitsocrypto.domain.repository.utils.DataConverter
import com.example.bitsocrypto.utils.network.NetworkState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /* @Provides
     @Singleton
     fun provideRetrofit(): CurrencyApiClient {
         return Retrofit.Builder()
             .baseUrl("https://api.bitso.com/v3/")
             .client(provideOkkHttpClient())
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(CurrencyApiClient::class.java)
     }*/

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

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        api: CurrencyRemoteDataSource,
        dao: CurrencyDao,
        network: NetworkState,
        map_currency: DataConverter
    ): CurrencyRepository = CurrencyRepositoryImpl(api, dao, network, map_currency)

    @Provides
    @Singleton
    fun provideRetrofit(url: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBitsoService(): CurrencyApiClient = provideRetrofit(
        "https://api.bitso.com/v3/",
        provideOkkHttpClient()
    ).create(CurrencyApiClient::class.java)

    @Provides
    @Singleton
    fun provideIconService(): IconApiClient = provideRetrofit(
        "https://raw.githubusercontent.com/",
        provideOkkHttpClient()
    ).create(IconApiClient::class.java)

    /* private val api: CurrencyRemoteDataSource, private
     val currencyDao: CurrencyDao*/
}