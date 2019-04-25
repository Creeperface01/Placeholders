package com.creeperface.nukkitx.placeholders.providers

import Sergey_Dertan.SRegionProtector.Main.SRegionProtectorMain
import Sergey_Dertan.SRegionProtector.Region.Flags.RegionFlags
import Sergey_Dertan.SRegionProtector.Region.RegionGroup
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import java.util.function.BiFunction

object SRegionProtectorProvider {

    private const val PREFIX = "srp_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val api = SRegionProtectorMain.getInstance()

        papi.visitorSensitivePlaceholder<String>("${PREFIX}current_region", BiFunction { p, _ ->
            val region = api.chunkManager.getRegion(p, p.level.name)
            return@BiFunction region?.name ?: ""
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}region_amount_creator", BiFunction { p, _ ->
            val region = api.regionManager.getPlayersRegionList(p, RegionGroup.CREATOR)
            return@BiFunction region?.size ?: 0
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}region_amount_owner", BiFunction { p, _ ->
            val region = api.regionManager.getPlayersRegionList(p, RegionGroup.OWNER)
            return@BiFunction region?.size ?: 0
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}region_amount_member", BiFunction { p, _ ->
            val region = api.regionManager.getPlayersRegionList(p, RegionGroup.MEMBER)
            return@BiFunction region?.size ?: 0
        })

        papi.visitorSensitivePlaceholder<Boolean>("${PREFIX}region_is_selling", BiFunction { p, _ ->
            val region = api.chunkManager.getRegion(p, p.level.name)
            return@BiFunction region?.isSelling ?: false
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}flag_state", BiFunction { p, params ->
            val region = api.chunkManager.getRegion(p, p.level.name)
            var flag = RegionFlags.FLAG_INVALID
            params.single()?.let {
                flag = RegionFlags.getFlagId(it)
            }
            if (flag == RegionFlags.FLAG_INVALID || region == null) return@BiFunction ""

            return@BiFunction if (region.getFlagState(flag) == RegionFlags.getStateFromString("allow", flag)) "allow" else "deny"
        })
    }
}