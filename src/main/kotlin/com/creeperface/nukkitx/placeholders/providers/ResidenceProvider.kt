package com.creeperface.nukkitx.placeholders.providers

import com.bekvon.bukkit.residence.Residence
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import java.util.*
import java.util.function.BiFunction

/**
 * @author CreeperFace
 */
object ResidenceProvider {

    private const val PREFIX = "res_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.visitorSensitivePlaceholder<String>("${PREFIX}current", BiFunction { p, _ ->
            Residence.getResidenceManager().getNameByLoc(p) ?: ""
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}current_owner", BiFunction { p, _ ->
            Residence.getResidenceManager().getByLoc(p)?.owner ?: ""
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}current_rent_days", BiFunction { p, _ ->
            Residence.getRentManager().getRentDays(Residence.getResidenceManager().getNameByLoc(p))
        })

        papi.visitorSensitivePlaceholder<Date>("${PREFIX}current_rent_ends", BiFunction { p, _ ->
            Residence.getLeaseManager().getExpireTime(Residence.getResidenceManager().getByLoc(p)?.name) ?: Date(0)
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}current_rented_by", BiFunction { p, _ ->
            val res = Residence.getResidenceManager().getByLoc(p) ?: return@BiFunction null

            if (Residence.getLeaseManager().save().containsKey(res.name)) res.owner else ""
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}current_rent_price", BiFunction { p, _ ->
            val res = Residence.getResidenceManager().getByLoc(p) ?: return@BiFunction 0

            Residence.getRentManager().getCostOfRent(res.name)
        })

        papi.visitorSensitivePlaceholder<Boolean>("${PREFIX}current_for_rent", BiFunction { p, _ ->
            val res = Residence.getResidenceManager().getByLoc(p) ?: return@BiFunction false

            Residence.getRentManager().isForRent(res.name)
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}current_sale_price", BiFunction { p, _ ->
            val res = Residence.getResidenceManager().getByLoc(p) ?: return@BiFunction 0

            Residence.getTransactionManager().getSaleAmount(res.name)
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}current_bank", BiFunction { p, _ ->
            val res = Residence.getResidenceManager().getByLoc(p) ?: return@BiFunction 0

            res.bank.storedMoney
        })

        //user
        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_rentables", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxRentables
        })

        papi.visitorSensitivePlaceholder<Double>("${PREFIX}user_block_cost", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).costPerBlock
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_sub_depth", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxSubzoneDepth
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_zones", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxZones
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_h", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxY
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_w", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxX
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_max_l", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).maxZ
        })

        papi.visitorSensitivePlaceholder<Boolean>("${PREFIX}user_cancreate", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroup(p).canCreateResidences()
        })

        papi.visitorSensitivePlaceholder<Int>("${PREFIX}user_amount", BiFunction { p, _ ->
            Residence.getResidenceManager().getOwnedZoneCount(p.name)
        })

        papi.visitorSensitivePlaceholder<Boolean>("${PREFIX}user_admin", BiFunction { p, _ ->
            Residence.getPermissionManager().isResidenceAdmin(p)
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}user_group", BiFunction { p, _ ->
            Residence.getPermissionManager().getGroupNameByPlayer(p)
        })
    }
}