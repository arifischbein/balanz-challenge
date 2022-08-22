package com.example.balanzchallenge.criptocurrencyfeature.ui.models

class CryptoUI(
    var imgUrl: String,
    var Symbol: String,
    var name: String,
    var price: String,
    var priceVariation: String
) {
    constructor(): this("","","","","")

    enum class Names(val fullName: String, val baseAsset: String){
        BTCBUSD("Bitcoin", "BTC"),
        ETHBUSD("Etherium", "ETH"),
        BNBBUSD("Binance Coin", "BNB"),
        LUNABUSD("Luna","LUNA"),
        SOLBUSD("Solana", "SOL"),
        LTCBUSD("Leitecoin", "LTC"),
        MATICBUSD("Matin Network", "MATIC"),
        AVAXBUSD("Avax", "AVAX"),
        XRPBUSD("Xrp", "XRP"),
        BUSDUSDT("Binance USD", "BUSD"),

        MISSING("Sin informacion", "");

        companion object{
            fun baseNamefromSymbol(sym: String): String {
                return try {
                    valueOf(sym).fullName
                } catch(e: IllegalArgumentException) {
                    MISSING.toString()
                }
            }
            fun baseAssetfromSymbol(sym: String): String {
                return try {
                    valueOf(sym).baseAsset
                } catch(e: IllegalArgumentException) {
                    MISSING.toString()
                }
            }
        }
    }

}

