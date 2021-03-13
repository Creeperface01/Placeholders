package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI

/**
 * @author CreeperFace
 */
object NukkitProvider {

    private const val PREFIX = ""

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.build<Boolean>("has_permission") {
            processParameters(true)

            visitorLoader {
                return@visitorLoader player.hasPermission(parameters.single()?.value ?: "")
            }
        }

        papi.build<Int>("player_item_in_hand_id") {
            processParameters(true)

            visitorLoader {
                return@visitorLoader player.inventory.itemInHand.id
            }
        }
    }
}