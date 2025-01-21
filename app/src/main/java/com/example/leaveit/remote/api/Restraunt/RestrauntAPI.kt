package com.example.leaveit.remote.api.Restraunt

import com.example.leaveit.BuildConfig
import com.example.leaveit.remote.entity.RestrauntEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RestrauntAPI {

    @GET("locationBasedList1")
    suspend fun getAllRegionPlace(
        @Query("MobileOS") mobileOs : String = "AND",
        @Query("MobileApp") mobileApp : String = "Leaveit",
        @Query("serviceKey") serviceKey : String = BuildConfig.TOUR_API_KEY,
        @Query("contentTypeId") contentTypedId : String = "39",
        @Query("mapX") longitutde : String,
        @Query("mapY") langitutde : String,
        @Query("numOfRows") numOfRows : String = "10",
        @Query("radius") radius : String = "3000",
        @Query("_type") type : String = "json"
    ) : RestrauntEntity
}