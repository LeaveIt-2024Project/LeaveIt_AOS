package com.example.leaveit.remote.api.Restraunt

import com.example.leaveit.BuildConfig
import com.example.leaveit.remote.entity.DetailRestrauntEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailRestrauntAPI {
    @GET("detailIntro1")
    suspend fun getDetailRestrauntData(
        @Query("MobileOS") mobileOs : String = "AND",
        @Query("MobileApp") mobileApp : String = "Leaveit",
        @Query("serviceKey") serviceKey : String = BuildConfig.TOUR_API_KEY,
        @Query("contentId") contentId : String,
        @Query("contentTypeId") contentTypedId : String = "39",
        @Query("_type") type : String = "json"
    ) : DetailRestrauntEntity
}