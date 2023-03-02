package com.example.bitsocrypto.domain

import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.example.bitsocrypto.utils.network.NetworkState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class GetCurrencyDetailUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: CurrencyRepository
    private lateinit var getCurrencyDetailUseCase: GetCurrencyDetailUseCase

    @RelaxedMockK
    private lateinit var networkMock: NetworkState

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        every { networkMock.isNetworkConnected() } returns true
        getCurrencyDetailUseCase = GetCurrencyDetailUseCase(repository, networkMock)
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
            Details(
                0,
                tickerMock,
                bookMock,
                false
            )
        )

        every { repository.getBook("btc_mxn") } returns mockk(relaxed = true) {
            every { blockingGet() } returns bookMock
        }
        every { repository.getTicker("btc_mxn") } returns mockk(relaxed = true) {
            every { blockingGet() } returns tickerMock
        }

        val result = Details(0, tickerMock, bookMock)

        list.add(result)

        advanceUntilIdle()

        coEvery {
            if (networkMock.isNetworkConnected()) {
                repository.insertDetail(list)
            }
        }
    }
}