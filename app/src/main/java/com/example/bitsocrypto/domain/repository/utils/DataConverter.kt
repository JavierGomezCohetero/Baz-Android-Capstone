package com.example.bitsocrypto.domain.repository.utils

import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker

object DataConverter {

    fun mapCurrencies(
        listCurrencies: List<Currency>,
        listIcons: List<IconResultModelItem>,
        listCurrenciesMap: (List<Currency>, List<IconResultModelItem>) -> List<Currency>
    ): List<Currency> = listCurrenciesMap(listCurrencies, listIcons)

    fun mapCurrencyDetail(
        bitsoId: Int,
        ticker: Ticker,
        book: Book,
        detailMap: (Int, Ticker, Book) -> Details
    ): Details = detailMap(bitsoId, ticker, book)
}