package com.example.bitsocrypto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsocrypto.databinding.FragmentListAsksBinding
import com.example.bitsocrypto.ui.adapter.AsksAdapter
import com.example.bitsocrypto.ui.viewmodel.CurrencyViewModel

class ListBidsFragment : Fragment() {
    private val viewModel: CurrencyViewModel by activityViewModels()

    private lateinit var binding: FragmentListAsksBinding

    private lateinit var adapter: AsksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListAsksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AsksAdapter()
        with(binding) {
            rvListAsksBid.adapter = adapter
            rvListAsksBid.setHasFixedSize(false)
            rvListAsksBid.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.book.observe(viewLifecycleOwner) {
            adapter.submitList(it.bids)
        }
    }
}