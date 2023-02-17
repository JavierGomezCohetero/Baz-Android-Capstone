package com.example.bitsocrypto.data.model

data class CurrencyDetailModel(
    val asks: List<AskBid> = emptyList(),
    val bids: List<AskBid> = emptyList(),
    val sequence: String = "",
    val updated_at: String = ""
)