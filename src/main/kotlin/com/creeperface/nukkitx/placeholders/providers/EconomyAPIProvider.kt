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

        papi.build<Double>("${PREFIX}money") {
            processParameters(true)
            visitorLoader {
                var money = api.myMoney(player)

                parameters["acc"]?.let {
                    money = money.round(it.value.toInt())
                }

                money
            }
        }
    }
}