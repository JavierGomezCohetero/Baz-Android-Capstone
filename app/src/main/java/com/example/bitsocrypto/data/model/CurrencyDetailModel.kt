package com.example.bitsocrypto.data.model

data class CurrencyDetailModel(
    val asks: List<AsksBidModel> = emptyList(),
    val bids: List<AsksBidModel> = emptyList(),
    val sequence: String = "",
    val updated_at: String = ""
)