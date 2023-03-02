package com.example.bitsocrypto.ui.viewmodel

import android.annotation.SuppressLint
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencies: GetCurrenciesUseCase,
    private val detail: GetCurrencyDetailUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<CurrencyState>()
    val state: LiveData<CurrencyState> = _state

    private var composite: CompositeDisposable? = CompositeDisposable()

    fun getCurrencies() {
        viewModelScope.launch {
            currencies.invoke().onEach { result ->
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

    @SuppressLint("CheckResult")
    fun getCurrencyDetail(bitsoId: Int, currencyName: String) {

        detail.invoke(bitsoId, currencyName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                composite?.add(it)
            }
            .doOnSuccess {
                _state.value = CurrencyState(
                    currencyDetail = it.data ?: Details(
                        0,
                        Ticker(high = "0.0", low = "0.0", last = "0.0"),
                        Book(),
                        false
                    ),
                    is_loading = false
                )
            }.subscribe()
    }

    private fun validationOnSubscribeSingle() {
        if (composite != null && !composite?.isDisposed!!) {
            composite?.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        validationOnSubscribeSingle()
        composite?.clear()
    }
}
