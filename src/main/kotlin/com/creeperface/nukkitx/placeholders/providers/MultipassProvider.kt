package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import ru.nukkit.multipass.Multipass
import java.util.function.BiFunction
import java.util.function.Function

/**
 * @author Zen
 */
object MultipassProvider {

    private const val PREFIX = "multipass_"

    fun registerPlaceholders(papi: PlaceholderAPI) {

        papi.visitorSensitivePlaceholder("${PREFIX}prefix", BiFunction { p, _ ->
            Multipass.getPrefix(p)
        })

        papi.visitorSensitivePlaceholder("${PREFIX}suffix", BiFunction { p, _ ->
            Multipass.getSuffix(p)
        })

        papi.visitorSensitivePlaceholder("${PREFIX}group", BiFunction { p, _ ->
            Multipass.getGroup(p)
        })

        papi.visitorSensitivePlaceholder("${PREFIX}groups", BiFunction { p, _ ->
            Multipass.getGroups(p).joinToString(", ")
        })

        papi.staticPlaceholder(
                name = "${PREFIX}group_prefix",
                loader = Function { params ->
                    Multipass.getGroupPrefix(params.single())
                }
        )

        papi.staticPlaceholder(
                name = "${PREFIX}group_suffix",
                loader = Function { params ->
                    Multipass.getGroupSuffix(params.single())
                }
        )

        papi.staticPlaceholder(
                name = "${PREFIX}group_priority",
                loader = Function { params ->
                    Multipass.getGroupPriority(params.single())
                }
        )
    }
}