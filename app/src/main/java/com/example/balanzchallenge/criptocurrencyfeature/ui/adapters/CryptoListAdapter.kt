package com.example.balanzchallenge.criptocurrencyfeature.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.balanzchallenge.criptocurrencyfeature.ui.models.CryptoUI
import com.example.balanzchallenge.databinding.ItemRecyclerCryptocurrencyBinding
import kotlin.properties.Delegates

class CryptoListAdapter(): RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    var cryptoList: List<CryptoUI> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged()}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerCryptocurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            setImg(cryptoList[position].imgUrl)
            setSymbol(cryptoList[position].Symbol)
            setName(cryptoList[position].Name)
            setPrice(cryptoList[position].Price)
            setPriceVariation(cryptoList[position].PriceVariation)
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun setData(cryptos: List<CryptoUI>){
        cryptoList = cryptos
    }

    class ViewHolder(val binding: ItemRecyclerCryptocurrencyBinding): RecyclerView.ViewHolder(binding.cardCryptoCurrencyItem){
        fun setImg(imgUrl: String){
            Glide.with(binding.root)
                .load(imgUrl)
                .centerCrop()
                .into(binding.imgCrypto)
        }
        fun setSymbol(symbol: String){
            binding.txtCryptoSymbol.text = symbol
        }
        fun setName(name: String){
            binding.txtCryptoName.text = name
        }
        fun setPrice(price: String){
            binding.txtCryptoPrice.text = price.toString()
        }
        fun setPriceVariation(priceVariation: String){
            binding.txtCryptoPriceChange.text = priceVariation.toString()
        }
    }
}