package com.example.bitsocrypto.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitsocrypto.utils.common.Resource
import com.example.bitsocrypto.data.model.CurrencyDetailModel
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.data.model.TickerModel
import com.example.bitsocrypto.domain.GetCurrenciesUseCase
import com.example.bitsocrypto.domain.GetCurrencyDetailUseCase
import com.example.bitsocrypto.domain.GetTickerUseCase
import com.example.bitsocrypto.data.model.CurrencyState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {

    private var getCurrencyUseCase = GetCurrenciesUseCase()
    private var getCurrencyDetail = GetCurrencyDetailUseCase()
    private var getTicker = GetTickerUseCase()

    private val _availableBooks = MutableLiveData<List<CurrencyModel>>()
    val availableBooks: LiveData<List<CurrencyModel>> get() = _availableBooks

    private val _book = MutableLiveData<CurrencyDetailModel>()
    val book: LiveData<CurrencyDetailModel> get() = _book

    private val _ticker = MutableLiveData<TickerModel>()
    val ticker: LiveData<TickerModel> get() = _ticker

    private val _state = MutableLiveData<CurrencyState>()
    val state: LiveData<CurrencyState> = _state


    init {
        getCurrencies()
    }


    fun getCurrencies() {
        viewModelScope.launch {
            getCurrencyUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _availableBooks.value = result.data ?: emptyList()
                        _state.value = CurrencyState(isLoading = false)
                        if (result.data.isNullOrEmpty()) {
                            _state.value = CurrencyState(
                                error = result.message ?: "Ha ocurrido un error con el servidor"
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = CurrencyState(
                            error = result.message ?: "Ha ocurrido un error con tu conexión"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CurrencyState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getCurrencyDetail(currencyName: String) {

        viewModelScope.launch {
            getCurrencyDetail.invoke(currencyName).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _book.value = result.data ?: CurrencyDetailModel()
                        _state.value = CurrencyState(isLoading = false)
                    }
                    is Resource.Error -> {
                        _state.value = CurrencyState(
                            error = result.message ?: "Ha ocurrido un error"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CurrencyState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getTicker(currencyName: String) {
        viewModelScope.launch {
            getTicker.invoke(currencyName).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _ticker.value = result.data ?: TickerModel()
                        _state.value = CurrencyState(isLoading = false)
                    }
                    is Resource.Error -> {
                        _state.value = CurrencyState(
                            error = result.message ?: "Ha ocurrido un error"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CurrencyState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
