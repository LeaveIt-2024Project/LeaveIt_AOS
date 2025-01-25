package com.example.leaveit.data.model

import com.example.leaveit.data.detail_restraunt.DetailRestrauntDataMapper
import com.example.leaveit.domain.model.DetailRestrauntDomainModel

data class DetailRestrauntDataModel(
    val firstmenu: String,
    val treatmenu: String,
    val parkingfood: String,
    val infocenterfood: String,
    val opentimefood: String,
    val restdatefood: String,
) : DetailRestrauntDataMapper {
    override fun toDomain(): DetailRestrauntDomainModel {
        return DetailRestrauntDomainModel(
            firstmenu = this.firstmenu,
            treatmenu = this.treatmenu,
            parkingfood = this.parkingfood,
            infocenterfood = this.infocenterfood,
            opentimefood = this.opentimefood,
            restdatefood = this.restdatefood
        )
    }
}
