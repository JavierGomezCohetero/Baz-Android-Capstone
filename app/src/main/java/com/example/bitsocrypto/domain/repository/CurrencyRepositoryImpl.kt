package com.example.bitsocrypto.domain.repository

import com.example.bitsocrypto.data.database.dao.CurrencyDao
import com.example.bitsocrypto.data.database.entities.toDatabase
import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.data.network.CurrencyRemoteDataSource
import com.example.bitsocrypto.domain.models.*
import com.example.bitsocrypto.domain.repository.utils.DataConverter
import com.example.bitsocrypto.utils.network.NetworkState
import io.reactivex.rxjava3.core.Single

class CurrencyRepositoryImpl(
    private val api: CurrencyRemoteDataSource,
    private val currencyDao: CurrencyDao,
    private val network: NetworkState,
    private val mapCurencies: DataConverter
) :
    CurrencyRepository {
    override suspend fun getAllCurrencies(): List<Currency> {
        return if (network.isNetworkConnected()) {
            clearCurrencies()
            val bitsoResponse = api.getCurrencies().map { it.toDomain() }
            val iconResponse = getIcons()
            val mapCurrencies = mapCurencies.mapCurrency(bitsoResponse, iconResponse)
            insertCurrencies(mapCurrencies)
            mapCurrencies
        } else {
            getAllCurrencyDatabase()
        }
    }

    override suspend fun getIcons(): List<IconResultModelItem> {
        return api.getIcons()
    }

    override fun getBook(id: String): Single<Book> = api.getBook(id).map { it.payload.toDomain() }

    override fun getTicker(id: String): Single<Ticker> {
        return api.getTicker(id).map {
            it.payload.toDomain()
        }
    }

    override suspend fun getAllCurrencyDatabase(): List<Currency> {
        return currencyDao.getAllCurrencies().map { it.toDomain() }
    }

    override suspend fun insertCurrencies(currencies: List<Currency>) {
        currencyDao.insertAll(currencies.map { it.toDatabase() })
    }

    override suspend fun clearCurrencies() {
        currencyDao.deleteAllCurrencies()
    }

    override fun getDetail(): List<Details> {
        return currencyDao.getAllDetails().map { it.toDatabase() }
    }

    override fun insertDetail(detail: List<Details>) {
        currencyDao.insertDetails(detail.map { it.toDatabase() })
    }

    override fun clearDetail() {
        currencyDao.deleteAllDetails()
    }
}