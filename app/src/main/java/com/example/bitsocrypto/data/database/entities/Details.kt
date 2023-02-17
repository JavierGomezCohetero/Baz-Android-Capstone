package com.example.bitsocrypto.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker

@Entity(tableName = "detail_table")
data class Detail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id") val detailsId: Int = 0,
    @ColumnInfo(name = "bitso_id") val bitsoId: Int = 0,
    @ColumnInfo(name = "tickers") val tickers: Ticker = Ticker(),
    @ColumnInfo(name = "book") val book: Book = Book(),
    @ColumnInfo(name = "from_database") val fromDatabase: Boolean = false
)

fun Details.toDatabase() = Detail(
    bitsoId = bitsoId,
    tickers = tickers,
    book = book,
    fromDatabase = fromDatabase
)
