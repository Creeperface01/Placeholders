package com.creeperface.nukkitx.placeholders

import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.TextFormat
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.creeperface.nukkitx.placeholders.providers.*
import java.util.function.Function

/**
 * @author CreeperFace
 */
class Placeholders : PluginBase() {

    override fun onEnable() {
        val api = PlaceholderAPI.getInstance()

        initConfig(api)

        NukkitProvider.registerPlaceholders(api)
        logger.info("${TextFormat.AQUA}Nukkit placeholders loaded")

        with(server.pluginManager) {
            getPlugin("LuckPerms")?.let {
                LuckPermsProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}LuckPerms placeholders loaded")
            }

            getPlugin("Factions")?.let {
                FactionsProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Factions placeholders loaded")
            }

            getPlugin("EconomyAPI")?.let {
                EconomyAPIProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}EconomyAPI placeholders loaded")
            }

            getPlugin("Residence")?.let {
                ResidenceProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Residence placeholders loaded")
            }

            getPlugin("SynapseAPI")?.let {
                SynapseAPIProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}SynapseAPI placeholders loaded")
            }
        }

    }

    private fun initConfig(api: PlaceholderAPI) {
        saveDefaultConfig()

        val cfg = config

        val section = cfg.getSection("placeholders")

        if (section.isNullOrEmpty()) {
            return
        }

        section.forEach { name, value ->
            if (value is Map<*, *> || value is List<*>) {
                return@forEach
            }

            api.staticPlaceholder<String>(name, Function { value.toString() })
        }
    }
}