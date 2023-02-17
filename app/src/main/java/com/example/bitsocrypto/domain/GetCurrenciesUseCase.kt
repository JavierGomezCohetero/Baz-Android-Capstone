package com.example.bitsocrypto.domain


import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.domain.repository.utils.DataConverter
import com.example.bitsocrypto.utils.extensions.stringConvert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Currency>>> = flow {
        try {
            /*Comentar emit Loading para el test GetCurrenciesUseCaseTest*/
            emit(Resource.Loading())
            val currencies = repository.getAllCurrencies()
            val icons = repository.getIcons()
            val mapCurrencies = mapCurrencies(currencies, icons)
            repository.clearCurrencies()
            repository.insertCurrencies(mapCurrencies)
            emit(Resource.Success(mapCurrencies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error ocurred"))
        } catch (e: IOException) {
            if (repository.getAllCurrencyDatabase().isEmpty()) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            } else {
                emit(Resource.Success(repository.getAllCurrencyDatabase()))
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    fun mapCurrencies(
        listCurrencies: List<Currency>,
        listIcons: List<IconResultModelItem>
    ): List<Currency> {
        return DataConverter.mapCurrencies(listCurrencies, listIcons) { _, _ ->
            var id = 0
            listCurrencies.map { currency ->
                currency.bitsoId = id
                id += 1
                val slug = stringConvert(currency.book)
                currency.img_url = listIcons.firstOrNull { icon ->
                    icon.symbol.uppercase() == slug
                }?.img_url.orEmpty()
                currency.name = listIcons.firstOrNull { icon ->
                    icon.symbol.uppercase() == slug
                }?.name ?: "Cryptocurrency"
            }
            listCurrencies
        }
    }
}