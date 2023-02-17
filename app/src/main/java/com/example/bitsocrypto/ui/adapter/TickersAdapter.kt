package com.example.bitsocrypto.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocrypto.data.model.AskBid
import com.example.bitsocrypto.databinding.ItemAsksBidsBinding

class TickersAdapter :
    ListAdapter<AskBid, TickersAdapter.TickersAdapter>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<AskBid>() {
        override fun areItemsTheSame(oldItem: AskBid, newItem: AskBid): Boolean {
            return oldItem.book == newItem.book
        }

        override fun areContentsTheSame(oldItem: AskBid, newItem: AskBid): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickersAdapter {
        val binding =
            ItemAsksBidsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TickersAdapter(binding)
    }

    override fun onBindViewHolder(holder: TickersAdapter, position: Int) = holder.bind()

    inner class TickersAdapter(private val binding: ItemAsksBidsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                val item = getItem(adapterPosition)
                tvAmount.text = item.amount
                tvPrice.text = item.price
            }
        }
    }
}