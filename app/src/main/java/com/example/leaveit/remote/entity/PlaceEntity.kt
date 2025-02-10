package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.PlaceDataModel
import com.example.leaveit.remote.placeview.PlaceEntityMapper
import com.google.gson.annotations.SerializedName


data class PlaceEntity(
    @SerializedName("addr1") val addr1: String,
    @SerializedName("areaCode") val areaCode: String,
    @SerializedName("cat") val cat: String,
    @SerializedName("contentId") val contentId: String,
    @SerializedName("contentTypeId") val contentTyepId: String,
    @SerializedName("firstImage") val firstImage: String,
    @SerializedName("longtitude") val longtitude: Double,
    @SerializedName("langitutde") val langitutde: Double,
    @SerializedName("tel") val tel: String,
    @SerializedName("title") val title: String
) : PlaceEntityMapper {
    override suspend fun toData(): PlaceDataModel {
        return PlaceDataModel(
            addr1 = this.addr1,
            areacode = this.areaCode,
            cat = this.cat,
            contentId = this.contentId,
            contenttypeid = this.contentTyepId,
            firstimage = this.firstImage,
            mapx = this.langitutde.toString(),
            mapy = this.longtitude.toString(),
            tel = this.tel,
            title = this.title
        )
    }
}
