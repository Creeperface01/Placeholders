package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import ru.nukkit.multipass.Multipass

/**
 * @author Zen
 */
object MultipassProvider {

    private const val PREFIX = "multipass_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.buildVisitorSensitive("${PREFIX}prefix") { p, _ ->
            Multipass.getPrefix(p)
        }.build()
        papi.buildVisitorSensitive("${PREFIX}suffix") { p, _ ->
            Multipass.getSuffix(p)
        }.build()
        papi.buildVisitorSensitive("${PREFIX}group") { p, _ ->
            Multipass.getGroup(p)
        }.build()
        papi.buildVisitorSensitive("${PREFIX}groups") { p, _ ->
            Multipass.getGroups(p).joinToString(", ")
        }.build()

        papi.buildStatic("${PREFIX}group_prefix") { params ->
            Multipass.getGroupPrefix(params.single()?.value ?: "")
        }.build()

        papi.buildStatic("${PREFIX}group_suffix") { params ->
            Multipass.getGroupSuffix(params.single()?.value ?: "")
        }.build()

        papi.buildStatic("${PREFIX}group_priority") { params ->
            Multipass.getGroupPriority(params.single()?.value ?: "")
        }.build()

    }
}