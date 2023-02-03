package com.example.bitsocrypto.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.databinding.FragmentCurrencyListBinding
import com.example.bitsocrypto.ui.adapter.CurrencyAdapter
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel


class CurrencyListFragment : Fragment(), CurrencyAdapter.onItemClicSelected {

    private lateinit var binding: FragmentCurrencyListBinding
    private val currencyViewModel: CurrencyViewModel by activityViewModels()
    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CurrencyAdapter(this)
        with(binding) {
            recyclerCurrency.adapter = adapter
            recyclerCurrency.setHasFixedSize(false)
            recyclerCurrency.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
        currencyViewModel.getCurrencies()
        currencyViewModel.availableBooks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        currencyViewModel.state.observe(viewLifecycleOwner){
            binding.pbCurrency.isVisible = it.isLoading
            binding.clError.isVisible = true
            binding.tvError.text = it.error
        }
    }

    override fun onItemSelected(currencyName: CurrencyModel) {
        val action =
            CurrencyListFragmentDirections.actionCurrencyListFragmentToCurrencyDetailFragment(
                currencyName
            )
        findNavController().navigate(action)
    }
}