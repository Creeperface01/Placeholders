package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.creeperface.nukkit.placeholderapi.util.round
import me.onebone.economyapi.EconomyAPI
import java.util.function.BiFunction

/**
 * @author CreeperFace
 */
object EconomyAPIProvider {

    private const val PREFIX = "economy_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val api = EconomyAPI.getInstance()

        papi.visitorSensitivePlaceholder("${PREFIX}money", BiFunction { p, params ->
            var money = api.myMoney(p)

            params["acc"]?.let {
                money = money.round(it.toInt())
            }

            return@BiFunction money
        })
    }
}