package com.example.balanzchallenge.criptocurrencyfeature.framework.models.binanceapi

import com.example.balanzchallenge.criptocurrencyfeature.ui.models.CryptoUI

data class PriceDayStatistic(
    val symbol: String,
    val priceChange: String,
    val priceChangePercent: String,
    val weightedAvgPrice: String,
    val prevClosePrice: String,
    val lastPrice: String,
    val lastQty: String,
    val bidPrice: String,
    val bidQty: String,
    val askPrice: String,
    val askQty: String,
    val openPrice: String,
    val highPrice: String,
    val lowPrice: String,
    val volume: String,
    val quoteVolume: String,
    val openTime: Long,
    val closeTime: Long,
    val firstId: Int,
    val lastId: Int,
    val count: Int
)