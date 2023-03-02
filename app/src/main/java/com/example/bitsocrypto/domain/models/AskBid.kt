package com.example.bitsocrypto.domain.models

import com.example.bitsocrypto.data.model.AskBid

fun AskBid.toDomain() = AskBid(
    this.amount,
    this.book,
    this.price
)