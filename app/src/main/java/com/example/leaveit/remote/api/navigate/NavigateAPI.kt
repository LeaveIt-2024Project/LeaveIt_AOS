package com.example.leaveit.remote.api.navigate

import com.example.leaveit.remote.entity.responsePath
import com.example.leaveit.remote.entity.responseRevserGeoCoding
import retrofit2.http.GET
import retrofit2.http.Query

interface NavigateAPI {

    @GET("map-reversegeocode/v2/gc")
    suspend fun getAllRegionPlace(
        @Query("coords") coords : String,
        @Query("orders") admcode : String = "admcode",
        @Query("output") output : String = "json"
    ) : responseRevserGeoCoding

    @GET("map-direction/v1/driving")
    suspend fun getPath(
        @Query("start") start : String,
        @Query("goal") goal : String
    ) : responsePath
}