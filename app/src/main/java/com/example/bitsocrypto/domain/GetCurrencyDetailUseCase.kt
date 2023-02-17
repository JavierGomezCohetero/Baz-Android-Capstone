package com.example.bitsocrypto.domain

import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.domain.repository.utils.DataConverter
import com.example.bitsocrypto.utils.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    private val listDetails = mutableListOf<Details>()
    suspend operator fun invoke(bitsoId: Int, currencyName: String): Flow<Resource<Details?>> =
        flow {
            try {
                emit(Resource.Loading())
                val book = repository.getBook(currencyName)
                val ticker = repository.getTicker(currencyName)
                val detail = mapDetail(bitsoId, ticker, book)
                listDetails.add(detail)
                repository.clearDetail()
                repository.insertDetail(listDetails)
                emit(Resource.Success(detail))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error ocurred"))
            } catch (e: IOException) {
                val responseDatabase = repository.getDetail().find { it.bitsoId == bitsoId }
                if (repository.getDetail().isEmpty()) {
                    emit(Resource.Error("Couldn't reach server. Check your internet connection."))
                } else {
                    if (responseDatabase?.bitsoId == bitsoId) {
                        emit(Resource.Success(responseDatabase))
                    } else {
                        emit(Resource.Success(Details(0, Ticker(high = "0.0", low = "0.0", last = "0.0"), Book(), false)))
                    }
                }
            }
        }

    fun mapDetail(bitsoId: Int, ticker: Ticker, book: Book): Details {
        return DataConverter.mapCurrencyDetail(bitsoId, ticker, book) { _, _, _ ->
            Details(
                bitsoId = bitsoId,
                tickers = ticker,
                book = book,
                fromDatabase = true
            )
        }
    }
}