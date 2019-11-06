package com.brentpanther.bitcoinwidget


import android.os.Build

import androidx.annotation.DrawableRes
import com.brentpanther.bitcoinwidget.R.drawable.*
import java.io.Serializable
import java.util.*

enum class Coin(val coinName: String, @param:DrawableRes val icon: Int, internal val icons: Map<String, Int>) {

    BTC("Bitcoin", ic_btc, mapOf("Light" to ic_btc, "Light Old" to ic_btc_gray, "Dark" to ic_btc_dark, "Dark Old" to ic_btc_dark_gray)) {
        override val units: List<Unit>
            get() = listOf(Unit("BTC", 1.0), Unit("mBTC", .001), Unit("μBTC", .000001))
    },
    ETH("Ethereum", ic_eth, mapOf("Light" to ic_eth, "Light Old" to ic_eth_gray)),
    XRP("Ripple", ic_xrp_black, mapOf("Light" to ic_xrp_black, "Light Old" to ic_xrp_gray, "Transparent" to ic_xrp_white, "Dark" to ic_xrp_white)),
    BCH("Bitcoin Cash", ic_bch, mapOf("Light" to ic_bch, "Light Old" to ic_bch_gray, "Dark" to ic_bch_dark, "Dark Old" to ic_bch_dark_gray)) {
        override val units: List<Unit>
            get() = listOf(Unit("BCH", 1.0), Unit("mBCH", .001), Unit("μBCH", .000001))
    },
    LTC("Litecoin", ic_ltc_gray, mapOf("Light" to ic_ltc, "Light Old" to ic_ltc_gray)) {
        override val units: List<Unit>
            get() = listOf(Unit("LTC", 1.0), Unit("lites", .001))
    },
    NEO("NEO", ic_neo, mapOf("Light" to ic_neo, "Light Old" to ic_neo_gray)),
    ADA("Cardano", ic_ada, mapOf("Light" to ic_ada, "Light Old" to ic_ada_gray)),
    XLM("Stellar", ic_xlm, mapOf("Light" to ic_xlm, "Light Old" to ic_xlm_gray, "Dark" to ic_xlm_gray)),
    IOTA("Iota", ic_iota, mapOf("Light" to ic_iota, "Light Old" to ic_iota_gray, "Dark" to ic_iota_gray)),
    DASH("Dash", ic_dash, mapOf("Light" to ic_dash, "Light Old" to ic_dash_gray, "Dark" to ic_dash_dark, "Dark Old" to ic_dash_dark_gray)),
    XMR("Monero", ic_xmr, mapOf("Light" to ic_xmr, "Light Old" to ic_xmr_gray, "Transparent Dark" to ic_xmr_dark)),
    XEM("NEM", ic_xem, mapOf("Light" to ic_xem, "Light Old" to ic_xem_gray, "Dark" to ic_xem_dark, "Dark Old" to ic_xem_dark_gray)),
    NANO("Nano", ic_nano, mapOf("Light" to ic_nano, "Light Old" to ic_nano_gray)),
    BTG("Bitcoin Gold", ic_btg, mapOf("Light" to ic_btg, "Light Old" to ic_btg_gray, "Dark" to ic_btg_dark, "Dark Old" to ic_btg_dark_gray)),
    ETC("Ethereum Classic", ic_etc, mapOf("Light" to ic_etc, "Light Old" to ic_etc_gray)),
    ZEC("Zcash", ic_zec, mapOf("Light" to ic_zec, "Light Old" to ic_zec_gray, "Dark" to ic_zec_dark, "Dark Old" to ic_zec_dark_gray,
            "Transparent Dark" to ic_zec, "Transparent Dark Old" to ic_zec)),
    XVG("Verge", ic_xvg, mapOf("Light" to ic_xvg, "Light Old" to ic_xvg_gray)),
    DOGE("Dogecoin", ic_doge, mapOf("Light" to ic_doge, "Light Old" to ic_doge_gray)),
    DCR("Decred", ic_dcr, mapOf("Light" to ic_dcr, "Light Old" to ic_dcr_gray)),
    PPC("Peercoin", ic_ppc, mapOf("Light" to ic_ppc, "Light Old" to ic_ppc_gray)),
    VTC("Vertcoin", ic_vtc, mapOf("Light" to ic_vtc, "Light Old" to ic_vtc_gray)),
    TRX("Tron", ic_trx, mapOf("Light" to ic_trx, "Light Old" to ic_trx_gray)),
    RDD("Reddcoin", ic_rdd, mapOf("Light" to ic_rdd, "Light Old" to ic_rdd_gray)),
    XTZ("Tezos", ic_xtz, mapOf("Light" to ic_xtz, "Light Old" to ic_xtz_gray)),
    BNB("Binance Coin", ic_bnb, mapOf("Light" to ic_bnb, "Light Old" to ic_bnb_gray));

    protected open val units: List<Unit> = emptyList()

    val unitNames: Array<String>
        get() = units.map { it.text}.toTypedArray()

    fun getUnitAmount(text: String) = units.firstOrNull { it.text == text }?.amount ?: 1.0

    companion object {

        internal var COIN_NAMES: SortedSet<String> = values().map { it.name }.toSortedSet()

        fun getVirtualCurrencyFormat(currency: String): String {
            return when (currency) {
                "BTC" ->
                    // bitcoin symbol added in Oreo
                    if (Build.VERSION.SDK_INT >= 26) "₿ #,###" else "Ƀ #,###"
                "LTC" -> "Ł #,###"
                else -> String.format("#,### %s", currency)
            }
        }
    }

}

class Unit(val text: String, val amount: Double) : Serializable
