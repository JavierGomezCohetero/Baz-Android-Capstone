package com.example.bitsocrypto.domain

import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.utils.common.Resource
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getAllCurrencies()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error ocurred"))
        } catch (e: IOException) {
            emit(Resource.Success(repository.getAllCurrencies()))
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}