package com.example.bitsocrypto.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocrypto.data.model.AsksBidModel
import com.example.bitsocrypto.databinding.ItemAsksBidsBinding

class BidAdapter : ListAdapter<AsksBidModel, BidAdapter.BidAdapter>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<AsksBidModel>() {
        override fun areItemsTheSame(oldItem: AsksBidModel, newItem: AsksBidModel): Boolean {
            return oldItem.book == newItem.book
        }

        override fun areContentsTheSame(oldItem: AsksBidModel, newItem: AsksBidModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidAdapter {
        val binding =
            ItemAsksBidsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BidAdapter(binding)
    }

    override fun onBindViewHolder(holder: BidAdapter, position: Int) = holder.bind()

    inner class BidAdapter(private val binding: ItemAsksBidsBinding) :
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