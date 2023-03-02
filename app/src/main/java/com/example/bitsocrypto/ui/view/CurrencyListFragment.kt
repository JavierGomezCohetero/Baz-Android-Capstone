package com.example.bitsocrypto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsocrypto.databinding.FragmentCurrencyListBinding
import com.example.bitsocrypto.domain.models.Currency
import com.example.bitsocrypto.ui.adapter.CurrencyAdapter
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel
import com.example.bitsocrypto.utils.network.NetworkState

class CurrencyListFragment : Fragment(), CurrencyAdapter.OnItemClickSelected {

    lateinit var binding: FragmentCurrencyListBinding
    private val currencyViewModel: CurrencyViewModel by activityViewModels()
    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        showData()
    }

    override fun onItemSelected(currencyName: Currency) {
        val action =
            CurrencyListFragmentDirections.actionCurrencyListFragmentToCurrencyDetailFragment(
                currencyName
            )
        findNavController().navigate(action)
    }

    private fun showData() {
        currencyViewModel.state.observe(viewLifecycleOwner) {
            if (!NetworkState(requireContext().applicationContext).isNetworkConnected()) {
                Toast.makeText(
                    context,
                    "Couldn't reach server. Check your internet connection.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.pbCurrency.isVisible = it.is_loading
            adapter.submitList(it.availableBooks)
        }
    }
}