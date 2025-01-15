package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.ReverseGeoCodingDataModel
import com.example.leaveit.remote.reversegedocoding.ReverseGeoCodingEntityMapperInterface
import com.google.gson.annotations.SerializedName

data class rootResponseReverseGeoCoding(
    val response : responseRevserGeoCoding
)

data class responseRevserGeoCoding(
    @SerializedName("results") val results: List<Result>,
    @SerializedName("status") val status: Status
)

data class Status(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String
)

data class Result(
    @SerializedName("code") val code: Code?,
    @SerializedName("name") val name: String?,
    @SerializedName("region") val region: Region?
) : ReverseGeoCodingEntityMapperInterface {
    override fun toDataModel(): ReverseGeoCodingDataModel {
        val sb = StringBuilder()
        sb.append(region?.area1?.name +" ")
        sb.append(region?.area2?.name +" ")
        sb.append(region?.area3?.name +" ")
        val result = sb.toString()
       return ReverseGeoCodingDataModel(
           address = result
       )
    }
}

data class Code(
    @SerializedName("id") val id: String,
    @SerializedName("mappingId") val mappingId: String,
    @SerializedName("type") val type: String
)


data class Region(
    @SerializedName("area0") val area0: Area1,
    @SerializedName("area1")  val area1: Area1,
    @SerializedName("area2")  val area2: Area1,
    @SerializedName("area3")  val area3: Area1,
    @SerializedName("area4") val area4: Area1? // null일 수 있음
)
data class Area1(
    @SerializedName("name") val name: String?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("icoordsd") val coords: Coords?
)

data class Coords(
    @SerializedName("center") val center: Center?
)

data class Center(
    @SerializedName("crs") val crs: String?,
    @SerializedName("x") val x: Float?,
    @SerializedName("y") val y: Float?
)

