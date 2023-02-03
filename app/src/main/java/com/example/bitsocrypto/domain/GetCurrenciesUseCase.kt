package com.example.bitsocrypto.domain

import com.example.bitsocrypto.R
import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.utils.extensions.stringConvert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrenciesUseCase {

    private val repository = CurrencyRepository()

    suspend operator fun invoke(): Flow<Resource<List<CurrencyModel>>> = flow {
        try {
            emit(Resource.Loading())
            val currencies = repository.getAllCurrencies()
            val icons = repository.getIcons()

            currencies.map {
                val slug = stringConvert(it.book)
                it.imgUrl = icons.find {
                    it.symbol.uppercase() == slug
                }?.img_url
                    ?: ""
                it.name = icons.find {
                    it.symbol.uppercase() == slug
                }?.name ?: "Cryptocurrency"
            }
            emit(Resource.Success(currencies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Hubo un error con el servidor"))
        } catch (e: IOException) {
            emit(Resource.Error("Hubo un error \n (Asegurate de tener una conexión de internet estable)"))
        }
    }
}