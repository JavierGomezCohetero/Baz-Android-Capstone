package com.example.bitsocrypto.domain

import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.utils.network.NetworkState
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    private val network: NetworkState
) {
    private val listDetails = mutableListOf<Details>()
    operator fun invoke(id: Int, currency_name: String): Single<Resource<Details>> {
        return if (network.isNetworkConnected()) {
            Single.zip(
                repository.getTicker(currency_name),
                repository.getBook(currency_name)
            ) { ticker, book ->
                val details = Details(id, ticker, book, false)
                if (ticker.book.isNotEmpty()) {
                    repository.clearDetail()
                    listDetails.add(details)
                    repository.insertDetail(listDetails)
                }
                Resource.Success(details)
            }
        } else {
            Single.create<Resource<Details>> {
                it.onSuccess(
                    Resource.Success(
                        repository.getDetail().find { it.bitso_id == id } ?: Details(
                            0,
                            Ticker(high = "0.0", low = "0.0", last = "0.0"),
                            Book(),
                            false
                        )
                    )
                )
            }
        }
    }
}