package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.model.AskBid
import com.example.bitsocrypto.data.model.CurrencyDetailModel

data class Book(
    val asks: List<AskBid> = listOf(),
    val bids: List<AskBid> = listOf(),
    val sequence: String = "",
    val updated_at: String = "",
)

fun CurrencyDetailModel.toDomain() = Book(
    asks.map { it.toDomain() },
    bids.map { it.toDomain() },
    sequence,
    updated_at
)
