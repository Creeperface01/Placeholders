package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.massivecraft.factions.P
import java.util.function.BiFunction

/**
 * @author CreeperFace
 */
object FactionsProvider {

    private const val PREFIX = "factions_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val plugin = P.p

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}faction", BiFunction { p, _ ->  plugin.getPlayerFactionTag(p)})
    }
}