package com.example.bitsocrypto.domain

import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.data.model.TickerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTickerUseCase {
    private val repository = CurrencyRepository()

    suspend operator fun invoke(currencyName: String): Flow<Resource<TickerModel>> = flow {
        try {
            emit(Resource.Loading())
            val ticker = repository.getTicker(currencyName)
            emit(Resource.Success(ticker))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Hubo un error con el servidor"))
        } catch (e: IOException) {
            emit(Resource.Error("Hubo un error \n (Asegurate de tener una conexión de internet estable)"))
        }
    }

}