package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import java.util.function.BiFunction

/**
 * @author CreeperFace
 */
object NukkitProvider {

    private const val PREFIX = ""

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.visitorSensitivePlaceholder(
                name = "has_permission",
                loader = BiFunction { p, params -> p.hasPermission(params.single() ?: "") }
        )

        papi.visitorSensitivePlaceholder(
                name = "player_item_in_hand_id",
                loader = BiFunction { p, _ -> p.inventory.itemInHand.id }
        )
    }
}