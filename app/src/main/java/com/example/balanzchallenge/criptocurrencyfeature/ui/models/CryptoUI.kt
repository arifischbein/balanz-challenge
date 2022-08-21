package com.example.balanzchallenge.criptocurrencyfeature.ui.models

class CryptoUI(
    var imgUrl: String,
    var Symbol: String,
    var Name: String,
    var Price: String,
    var PriceVariation: String
) {
    constructor(): this("","","","","")
}