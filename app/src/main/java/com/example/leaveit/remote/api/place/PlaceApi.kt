package com.example.leaveit.remote.api.place

import com.example.leaveit.remote.entity.PlaceEntity
import com.example.leaveit.remote.entity.SearchBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceApi {
    @GET("tour/area/cat/{cat}")
    suspend fun getAllRegionPlaceData(
        @Path("cat", encoded = true) cat : String,
        @Query("num") num : Int
    ) : Response<List<PlaceEntity>>


    @GET("tour/area/type/{cat}")
    suspend fun getSortByRegionPlaceData(
        @Path("cat", encoded = true) cat : String,
        @Query("areaCode") areaCode : String,
        @Query("num") num : Int
    ): Response<List<PlaceEntity>>

    @GET("tour/area/search/input/{query}")
    suspend fun getSearchPlaceData(
        @Path("query", encoded = true) query : String,
        @Query("num") num : Int
    ) : Response<List<PlaceEntity>>

    @POST("/tour/area/log")
    suspend fun setSearchLog(
        @Body log : SearchBody
    ) : Response<Unit>
}