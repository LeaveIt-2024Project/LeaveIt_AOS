package com.example.leaveit.remote.api.place

import com.example.leaveit.remote.entity.firstResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApi {
    //TODO 피드 api 완성되면 넣기
    //TODO 카테고리별로 API 던지기로 함


    //지역별로 관광지 가져오는 GET Method
    @GET("areaBasedList1")
    suspend fun getAllRegionPlace(
        @Query("MobileOS") mobileOs : String = "AND",
        @Query("MobileApp") mobileApp : String = "AppTest",
        @Query("serviceKey") serviceKey : String,
        @Query("contentTypeId") contentTypedId : String,
        @Query("numOfRows") numOfRows : String = "13112",
        @Query("_type") type : String = "json"
    ) : firstResponse

    @GET("areaBasedList1")
    suspend fun getSortByRegionPlace(
        @Query("MobileOS") mobileOs : String = "AND",
        @Query("MobileApp") mobileApp : String = "AppTest",
        @Query("serviceKey") serviceKey : String,
        @Query("contentTypeId") contentTypedId : String,
        @Query("numOfRows") numOfRows : String = "13112",
        @Query("areaCode") areaCode : String?,
        @Query("_type") type : String = "json"
    ) : firstResponse
}