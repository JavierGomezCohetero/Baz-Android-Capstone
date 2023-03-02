package com.example.bitsocrypto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bitsocrypto.R
import com.example.bitsocrypto.databinding.FragmentCurrencyDetailBinding
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.ui.adapter.TickersAdapter
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel
import com.example.bitsocrypto.utils.extensions.formatCurrency

class CurrencyDetailFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyDetailBinding
    private val viewModel: CurrencyViewModel by activityViewModels()
    private val args: CurrencyDetailFragmentArgs by navArgs()
    private lateinit var adapterAsks: TickersAdapter
    private lateinit var adapterBids: TickersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardDetail(args.currency)
        (adapterAsks) = TickersAdapter()
        adapterBids = TickersAdapter()
        with(binding) {
            rvListAsks.adapter = adapterAsks
            rvListAsks.setHasFixedSize(false)
            rvListAsks.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvListBids.adapter = adapterBids
            rvListBids.setHasFixedSize(false)
            rvListBids.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        listInitial()
    }

    private fun cardDetail(currency: Currency) {
        viewModel.getCurrencyDetail(currency.bitso_id, currency.book)
        viewModel.state.observe(viewLifecycleOwner) {
            with(binding) {
                if (it.error.isNotBlank()) {
                    pbCurrencyDetail.isVisible = true
                }
                pbCurrencyDetail.isVisible = it.is_loading
                tvNameBook.text = currency.name.uppercase()
                tvSlugBook.text = currency.book.uppercase()
                context?.let { it1 ->
                    Glide.with(it1).load(currency.img_url)
                        .placeholder(R.drawable.bitcoin_btc_logo).into(ivImageCurrency)
                }
                tvMaximumPrice.text = it.currencyDetail.tickers.high.toDouble().formatCurrency()
                tvMinimumPrice.text = it.currencyDetail.tickers.low.toDouble().formatCurrency()
                tvLastPrice.text = it.currencyDetail.tickers.last.toDouble().formatCurrency()
            }
        }
    }

    private fun listInitial() {
        viewModel.state.observe(viewLifecycleOwner) {
            adapterAsks.submitList(it.currencyDetail.book.asks)
            adapterBids.submitList(it.currencyDetail.book.bids)
        }
    }
}