package com.example.balanzchallenge.criptocurrencyfeature.ui.models

class CryptoUI(
    var imgUrl: String,
    var Symbol: String,
    var Name: String,
    var Price: Double,
    var PriceVariation: Double
) {
    constructor(): this("","","",0.0,0.0)
}