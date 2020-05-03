package com.creeperface.nukkitx.placeholders.providers

import cn.nukkit.utils.MainLogger
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.creeperface.nukkitx.placeholders.utils.lp.LPPlaceholderProvider
import com.creeperface.nukkitx.placeholders.utils.lp.PlaceholderPlatform
import net.luckperms.api.LuckPermsProvider
import java.util.function.BiFunction as BFunc

/**
 * @author CreeperFace
 */
object LuckPermsProvider : PlaceholderPlatform {

    private const val PREFIX = "luckperms_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val provider = LPPlaceholderProvider(this, LuckPermsProvider.get())

        try {
            for ((name, value) in provider.placeholders) {
                var name = name

                if (value.javaClass.declaredMethods.first { it.name == "handle" }.parameterCount == 4) { //static
//                    MainLogger.getLogger().info("LP: ${PREFIX + name}")
//
//                    papi.staticPlaceholder<String?>(PREFIX + name,
//                        Func { params ->
//                            provider.onPlaceholderRequest(
//                                null,
//                                getPlaceholderWithParams(name, params.getAll().values + params.getUnnamed())
//                            )
//                        }
//                    )
                } else { //dynamic
                    name = name.substring(0, name.length - 1)

//                    papi.visitorSensitivePlaceholder<String?>(PREFIX + name,
//                        BFunc { p: Player, params ->
//                            provider.onPlaceholderRequest(
//                                p,
//                                getPlaceholderWithParams(name, params.getAll().values + params.getUnnamed())
//                            )
//                        })
                }

                papi.visitorSensitivePlaceholder<String?>(
                        name = PREFIX + name,
                        loader = BFunc { p, params ->
                            provider.onPlaceholderRequest(
                                    p,
                                    getPlaceholderWithParams(name, params.getAll().values + params.getUnnamed())
                            )
                        }
                )
            }

        } catch (e: Exception) {
            MainLogger.getLogger().error("Could not load placeholders", e)
        }

    }

    private fun getPlaceholderWithParams(placeholder: String, params: List<String>): String {
        if (params.isEmpty()) return placeholder

        return placeholder + "_" + params.joinToString("_")
    }

    override fun formatTime(time: Int): String? {
        return PlaceholderAPI.getInstance().formatTime(time.toLong() * 1000)
    }

    override fun formatBoolean(value: Boolean): String? {
        return PlaceholderAPI.getInstance().formatBoolean(value)
    }
}