package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import org.itxtech.synapseapi.SynapseAPI

/**
 * @author CreeperFace
 */
object SynapseAPIProvider {

    private const val PREFIX = "synapse_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val synapse = SynapseAPI.getInstance()
        val entry = synapse.synapseEntries.values.singleOrNull() ?: return

        papi.build<String>("${PREFIX}server") {
            loader {
                entry.serverDescription
            }
        }

        papi.build<String>("${PREFIX}servers") {
            loader {
                (entry.clientData?.clientList?.values ?: emptyList()).joinToString(", ") { it.description }
            }
        }

        fun getClientData(desc: String) = entry.clientData?.clientList?.get(entry.clientData.getHashByDescription(desc))

        papi.build<Int>("${PREFIX}players") {
            loader {
                val server = parameters.single()?.value

                if (server != null) {
                    val dataEntry = getClientData(server) ?: return@loader 0

                    return@loader dataEntry.playerCount
                }

                entry.clientData?.clientList?.values?.sumBy { it.playerCount } ?: 0
            }
        }

        papi.build<Int>("${PREFIX}max_players") {
            loader {
                val server = parameters.single()?.value

                if (server != null) {
                    val dataEntry = getClientData(server) ?: return@loader 0

                    dataEntry.maxPlayers
                }

                entry.clientData?.clientList?.values?.sumBy { it.maxPlayers } ?: 0
            }
        }

        papi.build<String>("${PREFIX}status") {
            loader {
                val offlineValue = parameters["false"]?.value ?: "offline"

                val server = parameters["server"]?.value ?: parameters.single()?.value ?: return@loader offlineValue

                getClientData(server)?.let { parameters["true"]?.value ?: "online" } ?: offlineValue
            }
        }

    }
}
