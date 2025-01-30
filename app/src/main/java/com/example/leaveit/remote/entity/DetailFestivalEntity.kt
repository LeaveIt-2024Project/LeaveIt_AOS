package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.DetailFestivalDataModel
import com.example.leaveit.remote.placeview.DetailFestivalEntityMapper
import com.google.gson.annotations.SerializedName

data class DetailFestivalRootResponse(
    val response: detailFestivalResponse
)

data class detailFestivalResponse(
    @SerializedName("header") val header: detailFestivalHeader,
    @SerializedName("body") val body: detailFestivalBody,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int,
)

data class detailFestivalHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String
)

data class detailFestivalBody(
    @SerializedName("items") val detailFestivalItem : detailFestivalEntityList
)

data class detailFestivalEntityList(
    @SerializedName("item") val detailFestivalEntityList : List<DetailFestivalEntitiy>
)


data class DetailFestivalEntitiy(
    @SerializedName("contentid") val contentId: String,
    @SerializedName("contenttypeid") val contenttypeid: String,
    @SerializedName("eventenddate") val eventenddate: String?,
    @SerializedName("agelimit") val agelimit: String?,
    @SerializedName("usetimefestival") val usetimefestival: String?,
    @SerializedName("spendtimefestival") val spendtimefestival: String?,
    @SerializedName("bookingplace") val bookingplace: String?,
    @SerializedName("sponsor2tel") val sponsor2tel: String?,
    @SerializedName("playTime") val playTime: String?,
) : DetailFestivalEntityMapper {
    override fun toFestivalDataSourceModel(): DetailFestivalDataModel {
       return DetailFestivalDataModel(
           contentId = this.contentId,
           contenttypeid = this.contenttypeid,
           eventenddate = this.eventenddate,
           agelimit = this.agelimit,
           usetimefestival = this.usetimefestival,
           spendtimefestival = this.spendtimefestival,
           bookingplace = this.bookingplace,
           sponsor2tel = this.sponsor2tel,
           playTime = this.playTime
       )
    }
}