package com.example.bitsocrypto.domain.repository.utils

import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.utils.extensions.stringConvert
import javax.inject.Inject

class DataConverter @Inject constructor() {

    private fun mapCurrencies(
        list_currencies: List<Currency>,
        list_icons: List<IconResultModelItem>,
        list_currencies_map: (List<Currency>, List<IconResultModelItem>) -> List<Currency>
    ): List<Currency> = list_currencies_map(list_currencies, list_icons)

    fun mapCurrency(
        list_currencies: List<Currency>,
        list_icons: List<IconResultModelItem>
    ): List<Currency> {
        return mapCurrencies(list_currencies, list_icons) { _, _ ->
            var id = 0
            list_currencies.map { currency ->
                currency.bitso_id = id
                id += 1
                val slug = stringConvert(currency.book)
                currency.img_url = list_icons.firstOrNull { icon ->
                    icon.symbol.uppercase() == slug
                }?.img_url.orEmpty()
                currency.name = list_icons.firstOrNull { icon ->
                    icon.symbol.uppercase() == slug
                }?.name ?: "Cryptocurrency"
            }
            list_currencies
        }
    }
}