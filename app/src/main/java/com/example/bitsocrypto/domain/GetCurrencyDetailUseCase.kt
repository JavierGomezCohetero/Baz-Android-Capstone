package com.example.bitsocrypto.domain

import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.data.model.CurrencyDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrencyDetailUseCase {
    private val repository = CurrencyRepository()
    suspend operator fun invoke(currencyName: String): Flow<Resource<CurrencyDetailModel>> = flow {
        try {
            emit(Resource.Loading())
            val currencyDetail = repository.getCurrencyDetail(currencyName)
            emit(Resource.Success(currencyDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Hubo un error con el servidor"))
        } catch (e: IOException) {
            emit(Resource.Error("Hubo un error \n (Asegurate de tener una conexión de internet estable)"))
        }
    }
}