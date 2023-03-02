package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.model.TickerModel

data class Ticker(
    val ask: String = "",
    val bid: String = "",
    val book: String = "",
    val created_at: String = "",
    val high: String = "",
    val last: String = "",
    val low: String = "",
    val volume: String = "",
    val vwap: String = ""
)

fun TickerModel.toDomain() = Ticker(
    ask,
    bid,
    book,
    created_at,
    high,
    last,
    low,
    volume,
    vwap
)