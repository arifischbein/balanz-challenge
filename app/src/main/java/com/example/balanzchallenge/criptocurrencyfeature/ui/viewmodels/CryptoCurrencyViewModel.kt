package com.example.balanzchallenge.criptocurrencyfeature.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balanzchallenge.core.ui.BaseViewState
import com.example.balanzchallenge.criptocurrencyfeature.framework.BinanceApiClient
import com.example.balanzchallenge.criptocurrencyfeature.framework.BinanceApiService
import com.example.balanzchallenge.criptocurrencyfeature.framework.models.binanceapi.PriceDayStatistic
import com.example.balanzchallenge.criptocurrencyfeature.framework.models.binanceapi.PriceTicker
import com.example.balanzchallenge.criptocurrencyfeature.ui.models.CryptoUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.concurrent.thread

class CryptoCurrencyViewModel : ViewModel() {

    private val _viewStateLD = MutableLiveData<BaseViewState>()
    val viewStateLD: LiveData<BaseViewState> get() = _viewStateLD

    private val _cryptoListLD: MutableLiveData<MutableList<CryptoUI>> = MutableLiveData()
    val cryptoListLD: LiveData<MutableList<CryptoUI>> get() = _cryptoListLD

    private var cryptoListUi:MutableList<CryptoUI> = mutableListOf()

    fun getData(){
        viewModelScope.launch{
            _viewStateLD.value = BaseViewState.Loading
            val price = getPrice()
            val statistic = getStatistic()
            fillCriptoUI(price, statistic)
            _viewStateLD.value = BaseViewState.Ready
        }
    }

    private suspend fun getPrice(): List<PriceTicker>?{
        val call = BinanceApiClient.getRetrofit().create(BinanceApiService::class.java).getPrice(
            "[\"BTCBUSD\",\"ETHBUSD\",\"BNBBUSD\",\"LUNABUSD\",\"SOLBUSD\",\"LTCBUSD\",\"MATICBUSD\",\"AVAXBUSD\",\"XRPBUSD\",\"BUSDUSDT\"]"
        ).awaitResponse()
        return call.body()
    }
    private suspend fun getStatistic(): List<PriceDayStatistic>?{
        val call = BinanceApiClient.getRetrofit().create(BinanceApiService::class.java).getstatistics(
            "[\"BTCBUSD\",\"ETHBUSD\",\"BNBBUSD\",\"LUNABUSD\",\"SOLBUSD\",\"LTCBUSD\",\"MATICBUSD\",\"AVAXBUSD\",\"XRPBUSD\",\"BUSDUSDT\"]"
        ).awaitResponse()
        return call.body()
    }

    fun fillCriptoUI(price: List<PriceTicker>?, statistic: List<PriceDayStatistic>?){
        cryptoListUi = mutableListOf()
        var zipList = price!!.zip(statistic!!)
        zipList.forEach {
            var cryptoUiAux = CryptoUI()
            cryptoUiAux.imgUrl = "https://i0.wp.com/criptotendencia.com/wp-content/uploads/2022/02/Resumen-de-lo-mas-destacado-en-la-crypto-semana.jpg?fit=1200%2C675&ssl=1"
            cryptoUiAux.Symbol = it.first.symbol
            cryptoUiAux.Name = getName(it.first.symbol)
            cryptoUiAux.Price = it.first.price
            cryptoUiAux.PriceVariation = it.second.priceChangePercent

            cryptoListUi.add(cryptoUiAux)
        }
        _cryptoListLD.value = cryptoListUi
    }

    fun getName(symbol: String): String{
        return when(symbol){
            "BTCBUSD" -> {"Bitcoin"}
            "ETHBUSD" -> {"Etherium"}
            "BNBBUSD" -> {"Binance Coin"}
            "LUNABUSD" -> {"Luna"}
            "SOLBUSD" -> {"Solana"}
            "LTCBUSD" -> {"Leitecoin"}
            "MATICBUSD" -> {"Matin Network"}
            "AVAXBUSD" -> {"Avax"}
            "XRPBUSD" -> {"Xrp"}
            "BUSDUSDT" -> {"Binance USD"}

            else -> {""}
        }
    }

}