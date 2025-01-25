package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.RestrauntDataListModel
import com.example.leaveit.data.model.RestrauntDataModel
import com.example.leaveit.remote.restraunt.RestrauntEntityMapper
import com.google.gson.annotations.SerializedName

data class RestrauntEntity(
    @SerializedName("response") val restrauntResponse: RestrauntResponse?
)

data class RestrauntResponse(
    @SerializedName("header") val header: RestrauntHeader,
    @SerializedName("body") val restrauntBody: RestrauntBody?,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int
)

data class RestrauntHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String
)

data class RestrauntBody(
    @SerializedName("items") val items: RestrauntItems,
)

data class RestrauntItems(
    @SerializedName("item") val item: List<RestrauntItem>
): RestrauntEntityMapper {
    override fun toData(): RestrauntDataListModel {
        val temp = this.item.map {
            RestrauntDataModel(
                contentid = it.contentid,
                image = it.firstimage ?: "empty",
                longitutde = it.mapx,
                langtitude = it.mapy,
                title = it.title,
                addr = it.addr1
            )
        }
        return RestrauntDataListModel(
            dataList = temp
        )
    }
}

data class RestrauntItem(
    @SerializedName("addr1") val addr1: String,
    @SerializedName("addr2") val addr2: String?,
    @SerializedName("areacode") val areacode: String?,
    @SerializedName("booktour") val booktour: String?,
    @SerializedName("cat1") val cat1: String?,
    @SerializedName("cat2") val cat2: String?,
    @SerializedName("cat3") val cat3: String?,
    @SerializedName("contentid") val contentid: String,
    @SerializedName("contenttypeid") val contenttypeid: String,
    @SerializedName("cpyrhtDivCd") val cpyrhtDivCd: String?,
    @SerializedName("createdtime") val createdtime: String,
    @SerializedName("dist") val dist: String,
    @SerializedName("firstimage") val firstimage: String?,
    @SerializedName("firstimage2") val firstimage2: String?,
    @SerializedName("mapx") val mapx: String,
    @SerializedName("mapy") val mapy: String,
    @SerializedName("mlevel") val mlevel: String?,
    @SerializedName("modifiedtime") val modifiedtime: String,
    @SerializedName("sigungucode") val sigungucode: String?,
    @SerializedName("tel") val tel: String?,
    @SerializedName("title") val title: String
)