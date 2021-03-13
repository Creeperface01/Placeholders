package com.creeperface.nukkitx.placeholders.providers

import com.bekvon.bukkit.residence.Residence
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import java.util.*

/**
 * @author CreeperFace
 */
object ResidenceProvider {

    private const val PREFIX = "res_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        papi.build<String>("${PREFIX}current") {
//            processParameters(true)

            visitorLoader {
                Residence.getResidenceManager().getNameByLoc(player) ?: ""
            }
        }

        papi.build<String>("${PREFIX}current_owner") {
//            processParameters(true)

            visitorLoader {
                Residence.getResidenceManager().getByLoc(player)?.owner ?: ""
            }
        }
        papi.build<Int>("${PREFIX}current_rent_days") {
//            processParameters(true)

            visitorLoader {
                Residence.getRentManager().getRentDays(Residence.getResidenceManager().getNameByLoc(player))
            }
        }
        papi.build<Date>("${PREFIX}current_rent_ends") {
//            processParameters(true)

            visitorLoader {
                Residence.getLeaseManager().getExpireTime(Residence.getResidenceManager().getByLoc(player)?.name)
                    ?: Date(0)
            }
        }
        papi.build<String>("${PREFIX}current_rented_by") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader null

                if (Residence.getLeaseManager().save().containsKey(res.name)) res.owner else ""
            }
        }
        papi.build<Int>("${PREFIX}current_rent_price") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader 0

                Residence.getRentManager().getCostOfRent(res.name)
            }
        }
        papi.build<Boolean>("${PREFIX}current_for_rent") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader false

                Residence.getRentManager().isForRent(res.name)
            }
        }
        papi.build<Int>("${PREFIX}current_sale_price") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader 0

                Residence.getTransactionManager().getSaleAmount(res.name)
            }
        }
        papi.build<Boolean>("${PREFIX}current_for_sale") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader false

                Residence.getTransactionManager().isForSale(res.name)
            }
        }

        papi.build<Int>("${PREFIX}current_bank") {
//            processParameters(true)

            visitorLoader {
                val res = Residence.getResidenceManager().getByLoc(player) ?: return@visitorLoader 0

                res.bank.storedMoney
            }
        }

        //user
        papi.build<Int>("${PREFIX}user_max_rentables") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxRentables
            }
        }
        papi.build<Double>("${PREFIX}user_block_cost") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).costPerBlock
            }
        }
        papi.build<Int>("${PREFIX}user_max_sub_depth") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxSubzoneDepth
            }
        }
        papi.build<Int>("${PREFIX}user_max_zones") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxZones
            }
        }
        papi.build<Int>("${PREFIX}user_max_h") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxY
            }
        }
        papi.build<Int>("${PREFIX}user_max_w") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxX
            }
        }
        papi.build<Int>("${PREFIX}user_max_l") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).maxZ
            }
        }
        papi.build<Boolean>("${PREFIX}user_can_create") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroup(player).canCreateResidences()
            }
        }
        papi.build<Int>("${PREFIX}user_amount") {
//            processParameters(true)

            visitorLoader {
                Residence.getResidenceManager().getOwnedZoneCount(player.name)
            }
        }
        papi.build<Boolean>("${PREFIX}user_admin") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().isResidenceAdmin(player)
            }
        }
        papi.build<String>("${PREFIX}user_group") {
//            processParameters(true)

            visitorLoader {
                Residence.getPermissionManager().getGroupNameByPlayer(player)
            }
        }
    }
}