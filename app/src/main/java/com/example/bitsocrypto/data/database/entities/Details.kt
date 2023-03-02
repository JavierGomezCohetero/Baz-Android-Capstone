package com.example.bitsocrypto.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker

@Entity(tableName = "detail_table")
data class Detail(
    @PrimaryKey(autoGenerate = true)
    val detail_id: Int = 0,
    val bitso_id: Int = 0,
    val tickers: Ticker = Ticker(),
    val book: Book = Book(),
    val from_database: Boolean = false
)

fun Details.toDatabase() = Detail(
    bitso_id = bitso_id,
    tickers = tickers,
    book = book,
    from_database = from_database
)
