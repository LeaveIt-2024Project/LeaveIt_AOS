package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.DetailCultureDataModel
import com.example.leaveit.remote.placeview.DetailCultureEntityMapper
import com.google.gson.annotations.SerializedName

data class DetailCultureRootResponse(
    val response: detailCultureResponse
)

data class detailCultureResponse(
    @SerializedName("header") val header: detailCultureHeader,
    @SerializedName("body") val body: detailCultureBody,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int,
)

data class detailCultureHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String
)

data class detailCultureBody(
    @SerializedName("items") val detailCultureItem: detailCultureList
)

data class detailCultureList(
    @SerializedName("item") val detailCultureEntityList: List<DetailCultureEntitiy>
)


data class DetailCultureEntitiy(
    @SerializedName("contentid") val contentId: String,
    @SerializedName("contenttypeid") val contenttypeid: String,
    @SerializedName("infocenterculture") val infocenterculture: String?,
    @SerializedName("restdateculture") val restdateculture: String?,
    @SerializedName("chkbabycarriageculture") val chkbabycarriageculture: String?,
    @SerializedName("chkpetculture") val chkpetculture: String?,
    @SerializedName("parkingculture") val parkingculture: String?,
    @SerializedName("usetimeculture") val usetimeculture: String?,
    @SerializedName("usefee") val usefee: String?
) : DetailCultureEntityMapper {
    override fun toCultureDataSourceModel(): DetailCultureDataModel {
        return DetailCultureDataModel(
            contentid = this.contenttypeid,
            contenttypeid = this.contenttypeid,
            infocenterculture = this.infocenterculture,
            restdateculture = this.restdateculture,
            chkpetculture = this.chkpetculture,
            chkbabycarriageculture = this.chkbabycarriageculture,
            parkingculture = this.parkingculture,
            usetimeculture = this.usetimeculture,
            usefee = this.usefee
        )
    }
}