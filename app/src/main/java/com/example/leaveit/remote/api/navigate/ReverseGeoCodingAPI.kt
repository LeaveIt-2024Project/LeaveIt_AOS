package com.example.leaveit.remote.api.navigate

import com.example.leaveit.remote.entity.responseRevserGeoCoding
import com.example.leaveit.remote.entity.rootResponseReverseGeoCoding
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeoCodingAPI {

    @GET("map-reversegeocode/v2/gc")
    suspend fun getAllRegionPlace(
        @Query("coords") coords : String,
        @Query("orders") admcode : String = "admcode",
        @Query("output") output : String = "json"
    ) : responseRevserGeoCoding
}