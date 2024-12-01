package com.example.leaveit.remote.entity

import android.util.Log
import com.example.leaveit.data.model.PlaceDataModel
import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class firstResponse(
    val response : response
)

data class response(
    @SerializedName("header") val header: header,
    @SerializedName("body") val body: body,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int,
)

data class header(
    @SerializedName("resultCode") val resultCode: String = "없음",
    @SerializedName("resultMsg") val resultMsg: String = "없음"
)

data class body(
    @SerializedName("items") val placeEntityList : PlaceEntityList
) : RemoteMapper<PlaceViewDataModelList> {
    override suspend fun toData(temp: response): PlaceViewDataModelList {
        if (temp.body?.placeEntityList?.placeListEntity.isNullOrEmpty()) {
            throw Exception("placeEntityList 또는 placeListEntity가 비어 있습니다.")
        }

        val result = temp.body.placeEntityList.placeListEntity.mapNotNull { entity ->
            try {
                PlaceDataModel(
                    addr1 = entity.addr1 ?: "알 수 없음",
                    areacode = entity.areacode ?: "알 수 없음",
                    cat1 = entity.cat1 ?: "N/A",
                    cat2 = entity.cat2 ?: "N/A",
                    cat3 = entity.cat3 ?: "N/A",
                    contentId = entity.contentId ?: "empty",  // 안전한 변환
                    contenttypeid = entity.contenttypeid.toString(),  // 기본값 제공
                    firstimage = entity.firstimage ?: "empty",
                    firstimage2 = entity.firstimage2 ?: "empty",
                    mapx = entity.mapx ?: "0.0",
                    mapy = entity.mapy ?: "0.0",
                    sigungucode = entity.sigungucode.toIntOrNull() ?: 0,
                    tel = entity.tel ?: "문의",
                    title = entity.title ?: "제목 없음"
                )
            } catch (e: Exception) {
                Log.e("PlaceEntity", "Entity 변환 중 오류 발생: ${e.message}")
                null  // 변환 실패 시 해당 객체를 건너뜀
            }
        }


        return   PlaceViewDataModelList(placeDataModel = result)
    }
}

data class PlaceEntityList(
    @SerializedName("item") val placeListEntity: List<PlaceEntitiy>
) : RemoteMapper<response> {
    override suspend fun toData(temp: response): PlaceViewDataModelList {
        if (temp.body?.placeEntityList?.placeListEntity.isNullOrEmpty()) {
            throw Exception("placeEntityList 또는 placeListEntity가 비어 있습니다.")
        }

        val result = temp.body.placeEntityList.placeListEntity.mapNotNull { entity ->
            try {
                PlaceDataModel(
                    addr1 = entity.addr1 ?: "알 수 없음",
                    areacode = entity.areacode ?: "알 수 없음",
                    cat1 = entity.cat1 ?: "N/A",
                    cat2 = entity.cat2 ?: "N/A",
                    cat3 = entity.cat3 ?: "N/A",
                    contentId = entity.contentId ?: "empty",  // 안전한 변환
                    contenttypeid = entity.contenttypeid.toString(),  // 기본값 제공
                    firstimage = entity.firstimage ?: "empty",
                    firstimage2 = entity.firstimage2 ?: "empty",
                    mapx = entity.mapx ?: "0.0",
                    mapy = entity.mapy ?: "0.0",
                    sigungucode = entity.sigungucode.toIntOrNull() ?: 0,
                    tel = entity.tel ?: "문의",
                    title = entity.title ?: "제목 없음"
                )
            } catch (e: Exception) {
                Log.e("PlaceEntity", "Entity 변환 중 오류 발생: ${e.message}")
                null  // 변환 실패 시 해당 객체를 건너뜀
            }
        }


        return   PlaceViewDataModelList(placeDataModel = result)
    }
}

data class PlaceEntitiy(

    @SerializedName("addr1") val addr1: String,
    @SerializedName("addr2") val addr2: String?,
    @SerializedName("areacode") val areacode: String,
    @SerializedName("booktour") val booktour: String,
    @SerializedName("cat1") val cat1: String,
    @SerializedName("cat2") val cat2: String,
    @SerializedName("cat3") val cat3: String,
    @SerializedName("contentId") val contentId: String,
    @SerializedName("contenttypeid") val contenttypeid: String,
    @SerializedName("createdtime") val createdtime: String,
    @SerializedName("firstimage") val firstimage: String?,
    @SerializedName("firstimage2") val firstimage2: String?,
    @SerializedName("cpyrhtDivCd") val cpyrhtDivCd: String?,
    @SerializedName("mapx") val mapx: String,
    @SerializedName("mapy") val mapy: String,
    @SerializedName("mlevel") val mlevel: String,
    @SerializedName("modifiedtime") val modifiedtime: String,
    @SerializedName("sigungucode") val sigungucode: String,
    @SerializedName("tel") val tel: String?,
    @SerializedName("title") val title: String,
    @SerializedName("zipcode") val zipcode: String
)

