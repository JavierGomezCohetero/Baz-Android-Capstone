package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.database.entities.Detail

data class Details(
    val bitsoId: Int,
    val tickers: Ticker,
    val book: Book,
    val fromDatabase: Boolean = false
)

fun Detail.toDatabase() = Details(
    bitsoId,
    tickers,
    book,
    fromDatabase
)