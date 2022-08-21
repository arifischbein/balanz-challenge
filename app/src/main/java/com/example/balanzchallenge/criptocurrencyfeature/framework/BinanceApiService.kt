package com.example.balanzchallenge.criptocurrencyfeature.framework

import com.example.balanzchallenge.criptocurrencyfeature.framework.models.binanceapi.PriceDayStatistic
import com.example.balanzchallenge.criptocurrencyfeature.framework.models.binanceapi.PriceTicker
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApiService {

    @GET("ticker/price")
    fun getPrice(@Query("symbols", encoded = true) symbolName:String): Call<List<PriceTicker>>

    @GET("ticker/24hr")
    //fun getCrypto24statistics(@Query("symbols", encoded = true) symbolName:String): Call<List<PriceDayStatistic>>
    fun getstatistics(@Query("symbols", encoded = true) symbolName:String): Call<List<PriceDayStatistic>>
}