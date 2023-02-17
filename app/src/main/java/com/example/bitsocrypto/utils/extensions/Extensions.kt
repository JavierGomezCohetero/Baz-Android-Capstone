package com.example.bitsocrypto.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.bitsocrypto.R
import java.text.NumberFormat

fun stringConvert(nameCurrency: String): String {
    val delim = "_"
    return nameCurrency.split(delim)[0].uppercase()
}


fun Double.formatCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}

fun AppCompatImageView.loadUrl(url: String) = Glide.with(context).load(url).placeholder(R.drawable.bitcoin_btc_logo).into(this)
