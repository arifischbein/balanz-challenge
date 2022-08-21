package com.example.balanzchallenge.criptocurrencyfeature.framework

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BinanceApiClient {

    //Se puede inyectar
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.binance.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}