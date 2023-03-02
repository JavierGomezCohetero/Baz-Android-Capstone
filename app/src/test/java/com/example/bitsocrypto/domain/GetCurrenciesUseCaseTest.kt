package com.example.bitsocrypto.domain

import com.example.bitsocrypto.data.model.IconResultModelItem
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.domain.repository.CurrencyRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class GetCurrenciesUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: CurrencyRepository
    lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCurrenciesUseCase = GetCurrenciesUseCase(repository)
    }

    @After
    fun onAfter() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenTheApiReturnAnythingThenGetValuesFromApi() = runTest {
        val listMock = listOf<Currency>(
            Currency(
                0,
                "btc_mxn",
                "tradingview",
                "600",
                "7000000",
                "200000000",
                "0.00000060000",
                "20000",
                "10.00",
                "10",
                "",
                ""
            )
        )
        val iconMock = listOf<IconResultModelItem>(
            IconResultModelItem(
                "https://images/1",
                "BITCOIN",
                "btc",
                "",
            )
        )
        coEvery { repository.getAllCurrencies() } returns listMock

        val result = getCurrenciesUseCase()
        advanceUntilIdle()
        // val expectedList = repository.mapCurrencies(listMock, listIcons)
        result.collect {
            assertThat(it.data).isNotEmpty()
        }
        coVerify(exactly = 1) {
            repository.clearCurrencies()
        }
        coVerify(exactly = 1) {
            repository.insertCurrencies(listMock)
        }
    }
}