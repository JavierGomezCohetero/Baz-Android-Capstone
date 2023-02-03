package com.example.bitsocrypto.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bitsocrypto.R
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.databinding.FragmentCurrencyDetailBinding
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel
import com.example.bitsocrypto.utils.extensions.formatCurrency


class CurrencyDetailFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyDetailBinding
    private val viewModel: CurrencyViewModel by activityViewModels()
    private val args: CurrencyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardDetail(args.currency)
    }

    private fun cardDetail(nameCurrency: CurrencyModel) {
        viewModel.getTicker(nameCurrency.book)
        viewModel.getCurrencyDetail(nameCurrency.book)
        viewModel.state.observe(viewLifecycleOwner) {
            with(binding) {
                pbCurrencyDetail.isVisible = it.isLoading
            }
        }
        viewModel.ticker.observe(viewLifecycleOwner) {
            with(binding) {
                tvNameBook.text = nameCurrency.name.uppercase()
                tvSlugBook.text = nameCurrency.book.uppercase()
                context?.let { it1 ->
                    Glide.with(it1).load(nameCurrency.imgUrl)
                        .placeholder(R.drawable.bitcoin_btc_logo).into(ivImageCurrency)
                }
                tvMaximumPrice.text = it.high.toDouble().formatCurrency()
                tvMinimumPrice.text = it.low.toDouble().formatCurrency()
                tvLastPrice.text = it.last.toDouble().formatCurrency()
            }
        }
    }
}