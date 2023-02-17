package com.example.bitsocrypto.domain

import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.domain.repository.utils.DataConverter
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class GetCurrencyDetailUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: CurrencyRepository
    private lateinit var getCurrencyDetailUseCase: GetCurrencyDetailUseCase
    private var dataConverter: DataConverter = DataConverter

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCurrencyDetailUseCase = GetCurrencyDetailUseCase(repository)
    }

    @After
    fun onAfter() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenTheApiReturnACurrencyDetail() = runTest {
        val tickerMock = Ticker()
        val bookMock = Book()
        val list = mutableListOf<Details>(
            Details(0, tickerMock, bookMock, false)
        )


        coEvery { repository.getBook("btc_mxn") } returns bookMock
        coEvery { repository.getTicker("btc_mxn") } returns tickerMock

        val result = getCurrencyDetailUseCase.mapDetail(0, tickerMock,bookMock)

        list.add(result)

        advanceUntilIdle()

        coEvery {
            repository.insertDetail(list)
        }


    }
}