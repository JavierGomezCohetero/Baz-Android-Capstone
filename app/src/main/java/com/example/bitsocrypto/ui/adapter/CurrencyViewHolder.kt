package com.example.bitsocrypto.ui.adapter


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bitsocrypto.data.model.CurrencyModel
import com.example.bitsocrypto.databinding.ItemCurrencyBinding
import com.example.bitsocrypto.utils.extensions.formatCurrency
import com.example.bitsocrypto.utils.extensions.loadUrl

class CurrencyViewHolder(private val view: ItemCurrencyBinding) :
    ViewHolder(view.root) {

    fun render(currencyModel: CurrencyModel, onClickListener: CurrencyAdapter.onItemClicSelected) {
        with(view) {
            tvNameCurrency.text = currencyModel.name.uppercase()
            tvSlugCurrency.text = currencyModel.book.uppercase()
            tvMinimumPrice.text = currencyModel.minimum_price.toDouble().formatCurrency()
            tvMaximumPrice.text = currencyModel.maximum_price.toDouble().formatCurrency()
            ivCurrency.loadUrl(currencyModel.imgUrl)
            root.setOnClickListener { onClickListener.onItemSelected(currencyModel) }
        }
    }

}