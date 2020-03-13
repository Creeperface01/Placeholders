package com.creeperface.nukkitx.placeholders.providers

import cn.nukkit.utils.MainLogger
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import org.itxtech.synapseapi.SynapseAPI
import org.itxtech.synapseapi.SynapseEntry
import org.itxtech.synapseapi.utils.ClientData

/**
 * @author CreeperFace
 */
object SynapseAPIProvider {

    private const val PREFIX = "synapse_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val synapse = SynapseAPI.getInstance()
        val getEntry: () -> SynapseEntry? = {
            synapse.synapseEntries.values.singleOrNull()
        }

        papi.buildStatic("${PREFIX}server") loader@{ _ ->
            val entry = getEntry() ?: return@loader null
            entry.serverDescription
        }.build()

        papi.buildStatic("${PREFIX}servers") loader@{ _ ->
            val entry = getEntry() ?: return@loader null

            entry.clientData?.clientList?.values?.joinToString(", ") { it.description }
        }.build()

        fun getClientData(desc: String): ClientData.Entry? {
            val entry = getEntry() ?: return null
            return entry.clientData.clientList[entry.clientData.getHashByDescription(desc)]
        }

        papi.buildStatic("${PREFIX}players") loader@{ params ->
            MainLogger.getLogger().info(params.getAll().toString())
            val server = params.single() ?: return@loader 0

            val dataEntry = getClientData(server.value) ?: return@loader 0

            return@loader dataEntry.playerCount
        }.build()

        papi.buildStatic("${PREFIX}max_players") loader@{ params ->
            val server = params.single() ?: return@loader 0

            val dataEntry = getClientData(server.value) ?: return@loader 0

            dataEntry.maxPlayers
        }.build()

        papi.buildStatic("${PREFIX}status") loader@{ params ->
            val offlineValue = params["false"] ?: "offline"

            val server = params["server"] ?: params.single() ?: return@loader offlineValue.toString()

            return@loader getClientData(server.value)?.let { params["true"].toString() } ?: "online"
        }.build()
    }
}