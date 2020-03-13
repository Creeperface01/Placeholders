package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.creeperface.nukkit.placeholderapi.util.round
import me.onebone.economyapi.EconomyAPI

/**
 * @author CreeperFace
 */
object EconomyAPIProvider {

    private const val PREFIX = "economy_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val api = EconomyAPI.getInstance()

        papi.buildVisitorSensitive("${PREFIX}money") { p, params ->
            var money = api.myMoney(p)

            params["acc"]?.let {
                money = money.round(it.value.toInt())
            }

            money
        }.build()
    }
}