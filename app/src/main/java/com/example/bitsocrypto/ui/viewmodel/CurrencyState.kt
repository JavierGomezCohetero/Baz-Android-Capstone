package com.example.bitsocrypto.ui.viewmodel

import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker

data class CurrencyState(
    val availableBooks: List<Currency> = listOf(),
    val currencyDetail: Details = Details(
        0,
        Ticker(high = "0.0", last = "0.0", low = "0.0"),
        Book(),
        false
    ),
    val is_loading: Boolean = false,
    val error: String = ""
)
