package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.model.AskBid

data class AskBid(
    val amount: String = "",
    val book: String = "",
    val price: String = "",
)

fun AskBid.toDomain() = AskBid(
    this.amount,
    this.book,
    this.price
)