package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import ru.nukkit.multipass.Multipass

/**
 * @author Zen
 */
object MultipassProvider {

    private const val PREFIX = "multipass_"

    fun registerPlaceholders(papi: PlaceholderAPI) {

        papi.build<String>("${PREFIX}prefix") {
            visitorLoader {
                Multipass.getPrefix(player)
            }
        }

        papi.build<String>("${PREFIX}suffix") {
            visitorLoader {
                Multipass.getSuffix(player)
            }
        }

        papi.build<String>("${PREFIX}group") {
            visitorLoader {
                Multipass.getGroup(player)
            }
        }

        papi.build<String>("${PREFIX}groups") {
            visitorLoader {
                Multipass.getGroups(player).joinToString(", ")
            }
        }

        papi.build<String>("${PREFIX}group_prefix") {
            processParameters(true)
            loader {
                Multipass.getGroupPrefix(parameters.single()?.value)
            }
        }

        papi.build<String>("${PREFIX}group_suffix") {
            processParameters(true)
            loader {
                Multipass.getGroupSuffix(parameters.single()?.value)
            }
        }

        papi.build<Int>("${PREFIX}group_priority") {
            processParameters(true)
            loader {
                Multipass.getGroupPriority(parameters.single()?.value)
            }
        }
    }
}