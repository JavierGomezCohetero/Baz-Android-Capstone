package com.example.bitsocrypto.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bitsocrypto.databinding.ItemCurrencyBinding
import com.example.bitsocrypto.domain.models.Currency

class CurrencyAdapter(private val onClickListener: OnItemClickSelected) :
    ListAdapter<Currency, CurrencyViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
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

    interface OnItemClickSelected {
        fun onItemSelected(currencyName: Currency)
    }
}