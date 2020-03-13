package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI

/**
 * @author CreeperFace
 */
object NukkitProvider {

    private const val PREFIX = ""

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.buildVisitorSensitive("has_permission") { p, params ->
            p.hasPermission(params.single()?.value ?: "")
        }.build()

        papi.buildVisitorSensitive("player_item_in_hand_id") { p, _ ->
            p.inventory.itemInHand.id
        }.build()
    }
}