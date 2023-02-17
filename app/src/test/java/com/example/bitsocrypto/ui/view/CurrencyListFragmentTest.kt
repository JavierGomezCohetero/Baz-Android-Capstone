package com.example.bitsocrypto.ui.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.runner.AndroidJUnit4
import com.example.bitsocrypto.R
import com.example.bitsocrypto.domain.GetCurrenciesUseCase
import com.example.bitsocrypto.domain.GetCurrencyDetailUseCase
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyListFragmentTest {
    /*private val mockGetCurrenciesUseCase: GetCurrenciesUseCase = mockk(relaxed = true)
    private val mockGetCurrencyDetailUseCase: GetCurrencyDetailUseCase = mockk(relaxed = true)
    private var mockCurrencyViewModel: CurrencyViewModel =
        CurrencyViewModel(mockGetCurrenciesUseCase, mockGetCurrencyDetailUseCase)
    private lateinit var scenario: FragmentScenario<CurrencyListFragment>

    @After
    fun shutDown() {
        if (this::scenario.isInitialized) {
            scenario.moveToState(Lifecycle.State.DESTROYED)
        }
    }

    @Test
    fun testUI() {
        testFragment {
            assert(binding.recyclerCurrency.adapter?.itemCount != 0)
        }
    }

    fun testFragment(block: CurrencyListFragment.() -> Unit) {
        launchFragment().onFragment {
            it.block()
        }
    }

    private fun launchFragment() = launchFragmentInContainer(
        themeResId = R.style.Theme_BitsoCrypto,
        initialState = Lifecycle.State.RESUMED
    ) {
        val fragment = CurrencyListFragment()
        fragment.currencyViewModel = mockCurrencyViewModel
        fragment
    }.also {
        scenario = it
    }*/
}