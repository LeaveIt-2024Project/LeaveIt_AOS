package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.DetailRestrauntDataModel
import com.example.leaveit.remote.detail_restraunt.DetailRestrauntEntityMapper
import com.google.gson.annotations.SerializedName

data class DetailRestrauntEntity(
    val response: DetailRestrauntResponse
)

data class DetailRestrauntResponse(
    @SerializedName("header") val header: DetailRestrauntHeader,
    @SerializedName("body") val body: DetailRestrauntBody
)

data class DetailRestrauntHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String,
)


data class DetailRestrauntBody(
    @SerializedName("items") val items: DetailRestrauntItems
)

data class DetailRestrauntItems(
    @SerializedName("item") val item: List<DetailRestrauntItem>
)

data class DetailRestrauntItem(
    @SerializedName("firstmenu") val firstmenu: String,
    @SerializedName("treatmenu") val treatmenu: String,
    @SerializedName("parkingfood") val parkingfood: String,
    @SerializedName("infocenterfood") val infocenterfood: String,
    @SerializedName("opentimefood") val opentimefood: String,
    @SerializedName("restdatefood") val restdatefood: String,
) : DetailRestrauntEntityMapper {
    override fun toData(): DetailRestrauntDataModel {
        return DetailRestrauntDataModel(
            firstmenu = this.firstmenu,
            treatmenu = this.treatmenu,
            parkingfood = this.parkingfood,
            infocenterfood = this.infocenterfood,
            opentimefood = this.opentimefood,
            restdatefood = this.restdatefood
        )
    }
}