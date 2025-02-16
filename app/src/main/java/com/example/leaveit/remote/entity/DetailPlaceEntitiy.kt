package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.DetailPlaceDataDataModel
import com.example.leaveit.remote.placeview.DetailPlaceResponseMapper
import com.google.gson.annotations.SerializedName

data class detailPlaceRootResponse(
    val response: detailPlaceResponse
)

data class detailPlaceResponse(
    @SerializedName("header") val header: detailPlaceHeader,
    @SerializedName("body") val body: detailPlaceBody,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int,
)

data class detailPlaceHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String
)

data class detailPlaceBody(
    @SerializedName("items") val detailPlaceItem : detailPlaceEntityList
)

data class detailPlaceEntityList(
    @SerializedName("item") val detailPlaceEntityList : List<DetailPlaceEntitiy>
)


data class DetailPlaceEntitiy(
    @SerializedName("contentid") val contentId: String,
    @SerializedName("contenttypeid") val contenttypeid: String,
    @SerializedName("infocenter") val infocenter: String?,
    @SerializedName("restdate") val restdate: String?,
    @SerializedName("chkpet") val chkpet: String?,
    @SerializedName("parking") val parking: String?,
    @SerializedName("usetime") val usetime: String?,
    @SerializedName("chkbabycarriage") val chkbabycarriage : String?
) : DetailPlaceResponseMapper {
    override fun toPlaceDataSourceModel(): DetailPlaceDataDataModel {

        return DetailPlaceDataDataModel(
            contentId = contentId,
            contenttypeid = contenttypeid,
            infocenter = infocenter,
            restdate = restdate,
            chkpet = chkpet,
            parking = parking,
            usetime = usetime,
            chkbabycarriage = chkbabycarriage
        )
    }
}
