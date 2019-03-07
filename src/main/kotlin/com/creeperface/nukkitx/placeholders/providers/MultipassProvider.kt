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

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}prefix", BiFunction { p, _ ->
            Multipass.getPrefix(p)
        })

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}suffix", BiFunction { p, _ ->
            Multipass.getSuffix(p)
        })

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}group", BiFunction { p, _ ->
            Multipass.getGroup(p)
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}groups", BiFunction { p, _ ->
            Multipass.getGroups(p).joinToString(", ")
        })

        papi.staticPlaceholder<String?>(
            name = "${PREFIX}group_prefix",
            loader = Function { params ->
                Multipass.getGroupPrefix(params.single())
            },
            processParameters = true
        )

        papi.staticPlaceholder<String?>(
            name = "${PREFIX}group_suffix",
            loader = Function { params ->
                Multipass.getGroupSuffix(params.single())
            },
            processParameters = true
        )

        papi.staticPlaceholder<Int>(
            name = "${PREFIX}group_priority",
            loader = Function { params ->
                Multipass.getGroupPriority(params.single())
            },
            processParameters = true
        )
    }
}