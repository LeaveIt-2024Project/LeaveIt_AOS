package com.example.leaveit.remote.api.place

import com.example.leaveit.remote.entity.PlaceEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceApi {
    //TODO 피드 api 완성되면 넣기
    //TODO 카테고리별로 API 던지기로 함


    @GET("tour/area/cat/{cat}")
    suspend fun getAllRegionPlaceData(
        @Path("cat", encoded = true) cat : String,
        @Query("num") num : Int
    ) : Response<List<PlaceEntity>>


    @GET("tour/area/cat/{cat}/")
    suspend fun testAPI(
        @Path("cat", encoded = true) cat : String,
        @Query("num") num : Int
    ): Response<PlaceEntity>
}