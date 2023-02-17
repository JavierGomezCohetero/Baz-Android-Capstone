package com.example.bitsocrypto.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitsocrypto.domain.GetCurrenciesUseCase
import com.example.bitsocrypto.domain.GetCurrencyDetailUseCase
import com.example.bitsocrypto.domain.models.Book
import com.example.bitsocrypto.domain.models.Details
import com.example.bitsocrypto.domain.models.Ticker
import com.example.bitsocrypto.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
) : ViewModel() {

    private val _book = MutableLiveData<Details>()
    val book: LiveData<Details> get() = _book

    private val _state = MutableLiveData<CurrencyState>()
    val state: LiveData<CurrencyState> = _state

    fun getCurrencies() {
        viewModelScope.launch {
            getCurrenciesUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CurrencyState(result.data ?: emptyList(), is_loading = false)
                        if (result.data.isNullOrEmpty()) {
                            _state.value = CurrencyState(
                                error = result.message ?: ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = CurrencyState(
                            error = result.message ?: ""
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CurrencyState(is_loading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getCurrencyDetail(bitsoId: Int, currencyName: String) {

        viewModelScope.launch {
            this@CurrencyViewModel.getCurrencyDetailUseCase.invoke(bitsoId, currencyName)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = CurrencyState(
                                currencyDetail = result.data ?: Details(
                                    0,
                                    Ticker(high = "0.0", low = "0.0", last = "0.0"),
                                    Book(),
                                    false
                                ), is_loading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = CurrencyState(
                                error = result.message ?: ""
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = CurrencyState(is_loading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}
