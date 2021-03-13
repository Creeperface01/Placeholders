package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.massivecraft.factions.P

/**
 * @author CreeperFace
 */
object FactionsProvider {

    private const val PREFIX = "factions_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val plugin = P.p

        papi.build<String>("${PREFIX}faction") {
            visitorLoader {
                plugin.getPlayerFactionTag(player)
            }
        }
    }
}