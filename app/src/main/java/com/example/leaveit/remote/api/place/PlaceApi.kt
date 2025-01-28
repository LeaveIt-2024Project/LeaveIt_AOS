package com.example.leaveit.remote.api.place

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceApi {
    //TODO 피드 api 완성되면 넣기
    //TODO 카테고리별로 API 던지기로 함

    @GET("type/")
    suspend fun getAllRegionPlaceData(
        @Path("cat") category : String,
        @Query("num") num : Int
    )

    @GET("")
    suspend fun getSpecificRegionPlaceData(
        @Path("cat") category : String,
        @Path("areaCode") areadCode : String,
        @Query("num") num : Int
    )
}