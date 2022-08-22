package com.example.balanzchallenge.criptocurrencyfeature.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.balanzchallenge.core.utils.unaccent
import com.example.balanzchallenge.criptocurrencyfeature.ui.models.CryptoUI
import com.example.balanzchallenge.databinding.ItemRecyclerCryptocurrencyBinding
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.properties.Delegates

class CryptoListAdapter(): RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    companion object {
        const val CCL = 200
    }

    var cryptoList: List<CryptoUI> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged()}
    private var cryptoBackupList = mutableListOf<CryptoUI>()
    var filteredList = mutableListOf<CryptoUI>()
    private var filteredText = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerCryptocurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            setImg(cryptoList[position].imgUrl)
            setSymbol(cryptoList[position].Symbol)
            setName(cryptoList[position].name)
            if(cryptoList[position].name == "BUSDUSDT"){
                setUsdPrice(cryptoList[position].price)
            }else{
                setPrice(cryptoList[position].price)
            }
            setPriceVariation(cryptoList[position].priceVariation)
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun setData(cryptos: List<CryptoUI>){
        if(filteredText.isBlank()){
            cryptoList = cryptos
        }else{
            cryptoBackupList = cryptos.toMutableList()
            filter(filteredText)
        }

    }

    fun filter(text: String) {
        filteredList = mutableListOf()
        filteredText = text

        if (text.isEmpty()) {
            filteredList = cryptoBackupList
        } else {
            cryptoBackupList.forEach {
                if (it.Symbol.lowercase().unaccent().contains(text.lowercase()) ||
                    it.name.lowercase().unaccent().contains(text.lowercase())
                )
                    filteredList.add(it)
            }
        }
        cryptoList = filteredList

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
            val decimalPrice = price.toDouble()* CCL
            binding.txtCryptoPrice.text = String.format("%.4f",decimalPrice)
        }
        fun setUsdPrice(price: String){
            val decimalPrice = (1/price.toDouble()) * CCL
            binding.txtCryptoPrice.text = String.format("%.4f",decimalPrice)
        }
        fun setPriceVariation(priceVariation: String){
            binding.txtCryptoPriceChange.text = priceVariation
        }
    }
}