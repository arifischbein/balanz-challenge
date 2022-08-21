package com.example.balanzchallenge.criptocurrencyfeature.ui.models

class CryptoUI(
    var imgUrl: String,
    var Symbol: String,
    var name: String,
    var price: String,
    var priceVariation: String
) {
    constructor(): this("","","","","")

    enum class Names(val fullName: String){
        BTCBUSD("Bitcoin"),
        ETHBUSD("Etherium"),
        BNBBUSD("Binance Coin"),
        LUNABUSD("Luna"),
        SOLBUSD("Solana"),
        LTCBUSD("Leitecoin"),
        MATICBUSD("Matin Network"),
        AVAXBUSD("Avax"),
        XRPBUSD("Xrp"),
        BUSDUSDT("Binance USD"),

        MISSING("Sin informacion");

        companion object{
            fun fromSymbol(sym: String): String {
                return try {
                    valueOf(sym).toString()
                } catch(e: IllegalArgumentException) {
                    MISSING.toString()
                }
            }
        }
    }

}

