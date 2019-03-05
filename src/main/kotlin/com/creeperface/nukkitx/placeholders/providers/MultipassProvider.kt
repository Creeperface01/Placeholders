package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import ru.nukkit.multipass.Multipass
import java.util.function.BiFunction

/**
 * @author Zen
 */
object MultipassProvider {

    private const val PREFIX = "multipass_"

    fun registerPlaceholders(papi: PlaceholderAPI) {

        papi.visitorSensitivePlaceholder<String?>(
                "${PREFIX}prefix",
                BiFunction { p, _ -> Multipass.getPrefix(p) })

        papi.visitorSensitivePlaceholder<String?>(
                "${PREFIX}suffix",
                BiFunction { p, _ -> Multipass.getSuffix(p) })
    }
}