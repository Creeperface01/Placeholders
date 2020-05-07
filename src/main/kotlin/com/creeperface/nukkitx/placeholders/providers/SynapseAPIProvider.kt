package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import org.itxtech.synapseapi.SynapseAPI
import java.util.function.Function

/**
 * @author CreeperFace
 */
object SynapseAPIProvider {

    private const val PREFIX = "synapse_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val synapse = SynapseAPI.getInstance()
        val entry = synapse.synapseEntries.values.singleOrNull() ?: return

        papi.staticPlaceholder("${PREFIX}server", Function { entry.serverDescription })

        papi.staticPlaceholder(
                "${PREFIX}servers",
                Function {
                    (entry.clientData?.clientList?.values ?: emptyList()).joinToString(", ") { it.description }
                })

        fun getClientData(desc: String) = entry.clientData?.clientList?.get(entry.clientData.getHashByDescription(desc))

        papi.staticPlaceholder(
                name = "${PREFIX}players",
                loader = Function { params ->
                    val server = params.single() ?: return@Function 0

                    val dataEntry = getClientData(server) ?: return@Function 0

                    return@Function dataEntry.playerCount
                }
        )

        papi.staticPlaceholder(
                name = "${PREFIX}max_players",
                loader = Function { params ->
                    val server = params.single() ?: return@Function 0

                    val dataEntry = getClientData(server) ?: return@Function 0

                    return@Function dataEntry.maxPlayers
                }
        )

        papi.staticPlaceholder(
                name = "${PREFIX}status",
                loader = Function { params ->
                    val offlineValue = params["false"] ?: "offline"

                    val server = params["server"] ?: params.single() ?: return@Function offlineValue

                    return@Function getClientData(server)?.let { params["true"] ?: "online" } ?: offlineValue
                }
        )
    }
}
