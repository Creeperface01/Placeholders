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

        papi.buildVisitorSensitive("${PREFIX}faction") { p, _ ->
            plugin.getPlayerFactionTag(p)
        }.build()
    }
}