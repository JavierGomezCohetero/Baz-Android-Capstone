package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.database.entities.Detail

data class Details(
    val bitso_id: Int,
    val tickers: Ticker,
    val book: Book,
    val from_database: Boolean = false
)

fun Detail.toDatabase() = Details(
    bitso_id,
    tickers,
    book,
    from_database
)