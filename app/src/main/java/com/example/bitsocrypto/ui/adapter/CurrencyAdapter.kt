package com.example.bitsocrypto.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.databinding.ItemCurrencyBinding

class CurrencyAdapter(private val onClickListener: onItemClicSelected) :
    ListAdapter<CurrencyModel, CurrencyViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<CurrencyModel>() {
        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem.book == newItem.book
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val viewBinding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.render(getItem(position), onClickListener)
    }

    interface onItemClicSelected {
        fun onItemSelected(currencyName: CurrencyModel)
    }
}